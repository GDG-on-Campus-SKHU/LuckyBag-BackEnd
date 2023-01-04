package com.luckybag.luckybagbackend.domain;

import com.luckybag.luckybagbackend.domain.DTO.LuckyBagDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "luckybag")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LuckyBag extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column
    private String comment;

    @Column
    private int color;

    @Column
    private Long like;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime lastModifiedTime;


    public LuckyBagDTO toDto() {
        return LuckyBagDTO.builder()
                .memberId(memberId)
                .comment(comment)
                .color(color)
                .like(like)
                .createDate(createDate)
                .lastModifiedDate(lastModifiedDate)
                .build();

    }
}
