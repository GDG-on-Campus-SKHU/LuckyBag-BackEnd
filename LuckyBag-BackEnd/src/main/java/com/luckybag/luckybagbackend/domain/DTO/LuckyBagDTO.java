package com.luckybag.luckybagbackend.domain.DTO;


import com.luckybag.luckybagbackend.domain.Color;
import com.luckybag.luckybagbackend.domain.LuckyBag;
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

    private Long id;

    private Long memberId;

    private String comment;

    private Long colorId;

    private Long likeCount;

    private LocalDateTime createDate;

    private LocalDateTime lastModifiedDate;

    // 디티오를 엔티티로
    public LuckyBag toEntity() {
        return LuckyBag.builder().memberId(memberId).comment(comment)
                .colorId(colorId)
                .likeCount(likeCount)
                .createTime(createDate).lastModifiedTime(lastModifiedDate)
                .build();
    }
}
