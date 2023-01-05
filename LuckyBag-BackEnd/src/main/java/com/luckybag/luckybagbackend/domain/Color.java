package com.luckybag.luckybagbackend.domain;

import com.luckybag.luckybagbackend.domain.DTO.ColorDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "color")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String colorName;

    @OneToOne
    @JoinColumn(name = "id")
    private LuckyBag luckyBag;

    public ColorDTO toDto() {
        return ColorDTO.builder().colorId(id).build();
    }
}
