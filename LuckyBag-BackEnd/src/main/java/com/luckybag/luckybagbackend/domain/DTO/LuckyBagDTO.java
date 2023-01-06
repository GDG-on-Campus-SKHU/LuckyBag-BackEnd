package com.luckybag.luckybagbackend.domain.DTO;


import com.luckybag.luckybagbackend.domain.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LuckyBagDTO {

    private Long luckyBagId;

    private String comment; // 덕담 내용

    private Color color; // 복주머니 색깔

    private MemberDTO memberDTO; // 멤버 정보

}
