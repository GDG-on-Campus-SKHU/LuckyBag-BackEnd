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

    private Long id;

    private String nickname;

    private boolean hasLuckyBag;


}