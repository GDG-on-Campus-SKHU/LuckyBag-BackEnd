package com.luckybag.luckybagbackend.controller;

import com.luckybag.luckybagbackend.domain.DTO.*;
import com.luckybag.luckybagbackend.service.LuckyBagService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// [Get] /luckybags -> 모두 조회
// [Get] /luckybags/{id} -> 복주머니 리스트에서 하나만 조회
// [Post] /luckybags/{id} -> 저장
// [patch] /luckybas/{id} -> 수정
// [delete] /luckybags/{id} -> 삭제
@RestController
@RequiredArgsConstructor
public class ServiceController {
    private final LuckyBagService luckyBagService;

    @GetMapping("/luckybags")
    public Page<LuckyBagDTO> findAllWithDefault(@PageableDefault(size = 6) Pageable pageable) {
        Page<LuckyBagDTO> responses = luckyBagService.findAllWithPaging(pageable);
        return responses;
    }

    @GetMapping("/luckybags/{id}")
    public ResponseEntity<LuckyBagDTO> findByMemberId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(luckyBagService.findByMemberId(id));
    }


    @PostMapping("/luckybags/{id}")
    public ResponseEntity<LuckyBagDTO> save(@PathVariable("id") Long id, @RequestBody NewLuckyBagDTO newLuckyBagDTO) {
        LuckyBagDTO luckyBagDTO = luckyBagService.saveEntity(id, newLuckyBagDTO);
        return ResponseEntity.ok(luckyBagDTO);
    }


    @DeleteMapping("/luckybags/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        luckyBagService.deleteByMemberId(id);
        return ResponseEntity.ok("삭제 성공");
    }

    @PatchMapping("/luckybags/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody UpdateLuckyBagDTO updateLuckyBagDTO) {
        luckyBagService.update(id, updateLuckyBagDTO);
        return ResponseEntity.ok("수정 성공");
    }


}