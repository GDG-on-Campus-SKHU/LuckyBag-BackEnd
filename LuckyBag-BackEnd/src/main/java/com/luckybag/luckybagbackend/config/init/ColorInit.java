package com.luckybag.luckybagbackend.config.init;

import com.luckybag.luckybagbackend.domain.DTO.ColorDTO;
import com.luckybag.luckybagbackend.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ColorInit {
    private final ColorService colorService;
    @PostConstruct
    public void ColorInit() {
        ColorDTO Red = ColorDTO.builder().colorName("빨간색").build();
        ColorDTO Blue = ColorDTO.builder().colorName("파란색").build();
        ColorDTO Green = ColorDTO.builder().colorName("초록색").build();
        colorService.save(Red);
        colorService.save(Blue);
        colorService.save(Green);
    }
}
