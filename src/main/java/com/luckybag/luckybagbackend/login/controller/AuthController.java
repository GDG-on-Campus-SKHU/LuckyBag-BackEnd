package com.luckybag.luckybagbackend.login.controller;

import com.luckybag.luckybagbackend.domain.DTO.SignUpDTO;
import com.luckybag.luckybagbackend.login.domain.dto.LoginDTO;
import com.luckybag.luckybagbackend.login.domain.dto.LoginResponseDTO;
import com.luckybag.luckybagbackend.login.domain.dto.LogoutDTO;
import com.luckybag.luckybagbackend.login.domain.dto.TokenDTO;
import com.luckybag.luckybagbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);

        return loginResponseDTO;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpDTO signUpDTO) {
        authService.signUp(signUpDTO);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/log-out")
    public ResponseEntity<Void> logout(@RequestBody LogoutDTO logoutDTO) {
        authService.logout(logoutDTO);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<Void> reissue(@RequestBody TokenDTO tokenDTO) {
         authService.reissue(tokenDTO);

        return ResponseEntity.ok().build();
    }
}
