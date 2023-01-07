package com.luckybag.luckybagbackend.service;


import com.luckybag.luckybagbackend.domain.DTO.SignUpDTO;
import com.luckybag.luckybagbackend.domain.Member;
import com.luckybag.luckybagbackend.login.domain.dto.LoginDTO;
import com.luckybag.luckybagbackend.login.domain.dto.TokenDTO;
import com.luckybag.luckybagbackend.login.jwt.TokenProvider;
import com.luckybag.luckybagbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Member findEntityById(Long id) {
        return memberRepository.findById(id).get();
    }

    @Transactional
    public TokenDTO login(LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getMemberId(), loginDTO.getMemberPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDTO tokenDTO = tokenProvider.createToken(authentication);

        return tokenDTO;

    }

    public void signUp(SignUpDTO signUpDTO) {
        if (memberRepository.findByMemberId(signUpDTO.getMemberId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
        Member signUpMember = Member.builder()
                .memberId(signUpDTO.getMemberId())
                .memberPassword(signUpDTO.getMemberPassword())
                .nickname(signUpDTO.getNickName())
                .roles(Collections.singletonList("USER"))
                .build();
        memberRepository.saveAndFlush(signUpMember);

        signUpMember.encodePassword(passwordEncoder);
    }
}
