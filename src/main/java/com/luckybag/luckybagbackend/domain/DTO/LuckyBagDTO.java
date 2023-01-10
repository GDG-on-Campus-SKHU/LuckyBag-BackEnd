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

    private String comment;

    private Color color;

    private MemberDTO memberDTO;

}
