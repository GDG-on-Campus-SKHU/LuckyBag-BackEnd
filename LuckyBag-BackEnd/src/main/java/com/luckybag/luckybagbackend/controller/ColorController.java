package com.luckybag.luckybagbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ColorController {

// [Get] /members/{id}/colors -> 복주머니 색깔 조회

    @GetMapping("/members/{id}/colors")
    public ResponseEntity<Void> findByColor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(null);
    }
}