package com.luckybag.luckybagbackend.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long id; // 회원번호

    private String nickname; // 닉네임

    private boolean hasLuckyBag; // 복주머니 생성 유무


}