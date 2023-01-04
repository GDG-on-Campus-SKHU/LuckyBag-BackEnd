package com.luckybag.luckybagbackend.domain;

import com.luckybag.luckybagbackend.domain.DTO.LuckyBagDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "luckybag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LuckyBag extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column
    private String comment;

    @Column
    private Long colorId;

    @Column
    private Long likeCount;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime lastModifiedTime;


    public LuckyBagDTO toDto() {
        return LuckyBagDTO.builder()
                .memberId(memberId)
                .comment(comment)
                .colorId(colorId)
                .likeCount(likeCount)
                .createDate(createDate)
                .lastModifiedDate(lastModifiedDate)
                .build();

    }
}
