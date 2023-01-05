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
    private String comment;

    @Column
    private Long likeCount;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;


    public void update(LuckyBagDTO dto) {
        this.color.update(dto.getColorDTO().getColorId());
        this.comment = dto.getComment();
    }

    public LuckyBagDTO toDto() {
        return LuckyBagDTO.builder()
                .comment(comment)
                .colorDTO(color.toDto())
                .likeCount(likeCount)
                .memberDTO(member.toDTO())
                .build();
    }
}
