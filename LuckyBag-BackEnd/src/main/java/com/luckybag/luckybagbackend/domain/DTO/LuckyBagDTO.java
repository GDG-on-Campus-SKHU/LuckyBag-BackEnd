package com.luckybag.luckybagbackend.domain.DTO;


import com.luckybag.luckybagbackend.domain.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LuckyBagDTO {

    private Long memberId;

    private String comment;

    private Long colorId;

    private Long likeCount;

    private LocalDateTime createDate;

    private LocalDateTime lastModifiedDate;

}
