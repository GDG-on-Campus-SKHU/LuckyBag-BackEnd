package com.luckybag.luckybagbackend.login.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class LoginResponseDTO {
    Long id;
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Date refreshTokenExpirationTime;
}
