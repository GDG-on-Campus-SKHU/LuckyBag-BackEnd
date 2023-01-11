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
    private String memberId;

    private String memberPassword;

    private String nickName;
}
