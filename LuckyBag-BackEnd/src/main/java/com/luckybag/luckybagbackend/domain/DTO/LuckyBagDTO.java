package com.luckybag.luckybagbackend.domain.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LuckyBagDTO {

    private String comment;

    private ColorDTO colorDTO;

    private Long likeCount;

    private MemberDTO memberDTO;

}
