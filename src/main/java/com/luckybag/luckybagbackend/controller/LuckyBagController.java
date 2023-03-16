package com.luckybag.luckybagbackend.controller;

import com.luckybag.luckybagbackend.domain.DTO.*;
import com.luckybag.luckybagbackend.service.LuckyBagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequiredArgsConstructor
public class LuckyBagController {
    private final LuckyBagService luckyBagService;

    @GetMapping("/luckybags")
    public Page<LuckyBagResponseDTO> findAllWithDefault(@PageableDefault(size = 6) Pageable pageable) {
        Page<LuckyBagResponseDTO> responses = luckyBagService.findAll(pageable);
        return responses;
    }

    @GetMapping("/luckybags/{id}")
    public ResponseEntity<LuckyBagResponseDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(luckyBagService.findById(id));
    }

    @GetMapping("members/luckybags")
    public ResponseEntity<List<LuckyBagResponseDTO>> findAllByMember(Principal principal) {
        return ResponseEntity.ok(luckyBagService.findAllByMember(principal));
    }

    @PostMapping("/luckybags")
    public ResponseEntity<LuckyBagResponseDTO> save(@RequestBody LuckyBagRequestDTO luckyBagRequestDTO, Principal principal) {
        LuckyBagResponseDTO luckyBagResponseDTO = luckyBagService.save(luckyBagRequestDTO, principal);

        return ResponseEntity.ok(luckyBagResponseDTO);
    }

    @DeleteMapping("/luckybags/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        luckyBagService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/luckybags/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody LuckyBagUpdateDTO luckyBagUpdateDTO) {
        luckyBagService.update(id, luckyBagUpdateDTO);

        return ResponseEntity.ok().build();
    }
}