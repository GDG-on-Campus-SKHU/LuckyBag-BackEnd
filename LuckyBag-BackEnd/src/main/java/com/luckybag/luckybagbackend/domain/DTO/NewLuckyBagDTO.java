package com.luckybag.luckybagbackend.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewLuckyBagDTO {

    private String comment; // 내용

    private String colorName; // 복주머니 색깔

    private Long memberId;

}
