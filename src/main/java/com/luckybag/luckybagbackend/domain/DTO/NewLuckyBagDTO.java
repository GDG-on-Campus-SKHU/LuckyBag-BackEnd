package com.luckybag.luckybagbackend.domain.DTO;

import com.luckybag.luckybagbackend.domain.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewLuckyBagDTO {

    private String comment;

    private Color color;
}
