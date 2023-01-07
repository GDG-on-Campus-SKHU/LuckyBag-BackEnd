package com.luckybag.luckybagbackend.login.controller;

import com.luckybag.luckybagbackend.domain.DTO.SignUpDTO;
import com.luckybag.luckybagbackend.login.domain.dto.LoginDTO;
import com.luckybag.luckybagbackend.login.domain.dto.TokenDTO;
import com.luckybag.luckybagbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO memberLoginRequestDto) {
        TokenDTO tokenDTO = memberService.login(memberLoginRequestDto);
        return ResponseEntity.ok(tokenDTO.getGrantType() + " " + tokenDTO.getAccessToken());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        memberService.signUp(signUpDTO);
        return ResponseEntity.ok("회원가입 성공");
    }

}
