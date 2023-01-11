package com.luckybag.luckybagbackend.login.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LoginResponseDTO {
    TokenDTO tokenDTO;
    Long id;
}
