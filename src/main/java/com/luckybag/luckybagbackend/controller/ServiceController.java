package com.luckybag.luckybagbackend.controller;

import com.luckybag.luckybagbackend.domain.DTO.*;
import com.luckybag.luckybagbackend.service.LuckyBagService;
import com.luckybag.luckybagbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ServiceController {
    private final LuckyBagService luckyBagService;
    private final MemberService memberService;

    @GetMapping("/luckybags")
    public ResponseEntity<Slice<LuckyBagDTO>> findAllWithDefault(@PageableDefault(size = 6) Pageable pageable) {
        Slice<LuckyBagDTO> responses = luckyBagService.findAllWithPaging(pageable);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/luckybags/{id}")
    public ResponseEntity<?> findByLuckyBag(@PathVariable("id") Long id) {
        return ResponseEntity.ok(luckyBagService.findByLuckyBag(id));
    }

    @PostMapping("/members/{id}")
    public ResponseEntity<LuckyBagDTO> save(@PathVariable("id") Long id, @RequestBody NewLuckyBagDTO newLuckyBagDTO) {
        log.info("덕담 저장");
        LuckyBagDTO luckyBagDTO = luckyBagService.saveEntity(id, newLuckyBagDTO);
        return ResponseEntity.ok(luckyBagDTO);
    }

    @DeleteMapping("/luckybags/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        luckyBagService.deleteByMemberId(id);
        return ResponseEntity.ok("삭제 성공");
    }

    @GetMapping("member/{id}/luckybags")
    public ResponseEntity<List<LuckyBagDTO>> findLuckyBagsByMemberId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(memberService.findEntitiesById(id));
    }
    @PatchMapping("/luckybags/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody UpdateLuckyBagDTO updateLuckyBagDTO) {
        luckyBagService.update(id, updateLuckyBagDTO);
        return ResponseEntity.ok("수정 성공");
    }

}