package com.luckybag.luckybagbackend.domain.DTO;


import com.luckybag.luckybagbackend.domain.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LuckyBagResponseDTO {
    private Long id;

    private String comment;

    private Color color;
}
