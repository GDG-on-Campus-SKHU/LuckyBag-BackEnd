package com.luckybag.luckybagbackend.domain;

import com.luckybag.luckybagbackend.domain.DTO.LuckyBagDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "luckybag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LuckyBag extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "luckybag_id", nullable = false)
    private Long id;

    @Column
    private String color_name;

    @Column
    private String comment;

    @Column
    private Long likeCount;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void update(LuckyBagDTO luckyBagDTO) {
        this.color_name = luckyBagDTO.getColorName();
        this.comment = luckyBagDTO.getComment();
    }

    public LuckyBagDTO toDto() {
        return LuckyBagDTO.builder()
                .comment(comment)
                .colorName(color_name)
                .memberDTO(member.toDTO())
                .build();
    }
}
