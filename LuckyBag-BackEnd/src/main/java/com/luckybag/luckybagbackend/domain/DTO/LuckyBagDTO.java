package com.luckybag.luckybagbackend.domain.DTO;


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

    private int color;

    private Long like;

    private LocalDateTime createDate;

    private LocalDateTime lastModifiedDate;

}
