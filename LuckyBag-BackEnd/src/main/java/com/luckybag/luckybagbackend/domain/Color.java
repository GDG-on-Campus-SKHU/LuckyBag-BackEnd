package com.luckybag.luckybagbackend.domain;

import com.luckybag.luckybagbackend.domain.DTO.ColorDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "color")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id", nullable = false)
    private Long id;

    @Column
    private String colorName;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id")
    private List<LuckyBag> luckyBags;

    public void update(Long id) {
        this.id = id;
    }
    public ColorDTO toDto() {
        return ColorDTO.builder().colorId(id).build();
    }
}
