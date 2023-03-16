package com.luckybag.luckybagbackend.domain;

import com.luckybag.luckybagbackend.domain.DTO.LuckyBagResponseDTO;
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

    private Color color;
    @Column
    private String comment;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void update(String updateComment) {
        this.comment = updateComment;
    }

    public LuckyBagResponseDTO toDTO() {
        return LuckyBagResponseDTO.builder()
                .id(id)
                .comment(comment)
                .color(color)
                .build();
    }

}
