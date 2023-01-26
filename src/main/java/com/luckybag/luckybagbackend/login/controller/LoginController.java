package com.luckybag.luckybagbackend.login.controller;

import com.luckybag.luckybagbackend.domain.DTO.SignUpDTO;
import com.luckybag.luckybagbackend.login.domain.dto.LoginDTO;
import com.luckybag.luckybagbackend.login.domain.dto.LoginResponseDTO;
import com.luckybag.luckybagbackend.login.domain.dto.LogoutDTO;
import com.luckybag.luckybagbackend.login.domain.dto.TokenDTO;
import com.luckybag.luckybagbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final MemberService memberService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO memberLoginRequestDto) {
        TokenDTO tokenDTO = memberService.login(memberLoginRequestDto);
        Long id = memberService.findId(memberLoginRequestDto);
        return LoginResponseDTO.builder().id(id)
                .grantType(tokenDTO.getGrantType())
                .accessToken(tokenDTO.getAccessToken())
                .refreshToken(tokenDTO.getRefreshToken())
                .refreshTokenExpirationTime(tokenDTO.getRefreshTokenExpirationTime())
                .build();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        memberService.signUp(signUpDTO);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/log-out")
    public ResponseEntity<?> logout(@RequestBody LogoutDTO logoutDTO) {
        log.info("로그아웃");
        return memberService.logout(logoutDTO);
    }

    //access 토큰 재발급
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenDTO tokenDTO) {
        log.info("토큰 재발급");
        return memberService.reissue(tokenDTO);
    }
}
