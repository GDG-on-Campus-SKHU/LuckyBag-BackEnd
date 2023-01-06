package com.luckybag.luckybagbackend.service;

import com.luckybag.luckybagbackend.domain.Color;
import com.luckybag.luckybagbackend.domain.DTO.ColorDTO;
import com.luckybag.luckybagbackend.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColorService {
    private final ColorRepository colorRepository;

    public void save(ColorDTO colorDTO) {
        Color color = Color.builder()
                .colorName(colorDTO.getColorName()).build();
        colorRepository.saveAndFlush(color);
    }

    public Color findColor(Long id) {
        return colorRepository.findById(id).get();
    }
}
