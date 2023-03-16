package com.luckybag.luckybagbackend.service;


import com.luckybag.luckybagbackend.domain.DTO.SignUpDTO;
import com.luckybag.luckybagbackend.domain.Member;
import com.luckybag.luckybagbackend.login.domain.dto.LoginDTO;
import com.luckybag.luckybagbackend.login.domain.dto.LoginResponseDTO;
import com.luckybag.luckybagbackend.login.domain.dto.LogoutDTO;
import com.luckybag.luckybagbackend.login.domain.dto.TokenDTO;
import com.luckybag.luckybagbackend.login.jwt.TokenProvider;
import com.luckybag.luckybagbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 USERNAME에 맞는 사용자가 존재하지 않습니다."));
    }


    public LoginResponseDTO login(LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getUserPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDTO tokenDTO = tokenProvider.createToken(authentication);

        //refresh token Redis에 저장
        redisTemplate.opsForValue().set("RT:" + authentication.getName(),
                tokenDTO.getRefreshToken(),
                tokenDTO.getRefreshTokenExpirationTime().getTime(),
                TimeUnit.MILLISECONDS);

        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                .grantType(tokenDTO.getGrantType())
                .accessToken(tokenDTO.getAccessToken())
                .refreshToken(tokenDTO.getRefreshToken())
                .refreshTokenExpirationTime(tokenDTO.getRefreshTokenExpirationTime())
                .build();

        return loginResponseDTO;

    }

    public void logout(LogoutDTO logoutDTO) {
        //accessToken 검증
        if (!tokenProvider.validateToken(logoutDTO.getAccessToken())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다");
        }

        //Access Token에서 authentication 가져온다.
        Authentication authentication = tokenProvider.getAuthentication(logoutDTO.getAccessToken());

        //Redis에서 해당 authentication으로 저장된 refresh token이 있을 경우 삭제한다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            redisTemplate.delete("RT:" + authentication.getName());
        }

        //해당 AccessToken 유효시간 가지고 와서 BlackList로 저장하기
        Long expiration = tokenProvider.getExpiration(logoutDTO.getAccessToken());

        redisTemplate.opsForValue().set(logoutDTO.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);
    }

    public void reissue(TokenDTO tokenDTO) {
        if (!tokenProvider.validateToken(tokenDTO.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 정보가 유효하지 않습니다.");
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenDTO.getAccessToken());

        //Redis에서 아이디 기반으로 저장된 refresh token 값을 가져온다.
        String refreshToken = (String) redisTemplate.opsForValue().get("RT:" + authentication.getName());

        if (!refreshToken.equals(tokenDTO.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 정보가 일치하지 않습니다.");
        }

        //로그아웃되어 Redis에 refresh 토큰이 없을 경우
        if (ObjectUtils.isEmpty(refreshToken)) {
            throw new RuntimeException("로그아웃 상태입니다.");
        }

        //새로운 토큰 생성
        TokenDTO newToken = tokenProvider.createToken(authentication);

        //refreshToken Redis 업데이트
        redisTemplate.opsForValue().set("RT:" + authentication.getName(),
                newToken.getRefreshToken(),
                newToken.getRefreshTokenExpirationTime().getTime(),
                TimeUnit.MILLISECONDS);
    }

    public void signUp(SignUpDTO signUpDTO) {
        if (memberRepository.findByUsername(signUpDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        Member member = Member.builder()
                .username(signUpDTO.getUsername())
                .userPassword(signUpDTO.getUserPassword())
                .nickname(signUpDTO.getNickName())
                .roles(Collections.singletonList("USER"))
                .build();

        memberRepository.save(member);

        member.encodePassword(passwordEncoder);
    }
}
