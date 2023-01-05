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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "memberId")
    private Long memberId;

    @Column
    private String comment;

    @Column
    private Long colorId;

    @Column
    private Long likeCount;

    @OneToOne(mappedBy = "luckyBag",fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(mappedBy = "luckyBag", fetch = FetchType.LAZY)
    private Color color;
    public void update(LuckyBagDTO dto) {
        this.colorId = dto.getColorDTO().getColorId();
        this.comment = dto.getComment();
    }

    public LuckyBagDTO toDto() {
        return LuckyBagDTO.builder()
                .memberDTO(member.toDTO())
                .comment(comment)
                .colorDTO(color.toDto())
                .likeCount(likeCount)
                .build();

    }
}
