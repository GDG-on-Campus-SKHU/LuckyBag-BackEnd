package com.luckybag.luckybagbackend.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
    private String username;

    private String userPassword;

    private String nickName;
}
