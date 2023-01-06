package com.luckybag.luckybagbackend.domain;

import com.luckybag.luckybagbackend.domain.DTO.ColorDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "color")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id", nullable = false)
    private Long id;

    @Column
    private String colorName;



    public ColorDTO toDto() {
        return ColorDTO.builder().colorName(colorName).build();
    }
}