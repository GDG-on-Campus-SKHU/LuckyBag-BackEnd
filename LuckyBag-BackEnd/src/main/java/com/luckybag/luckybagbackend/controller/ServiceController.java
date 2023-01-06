package com.luckybag.luckybagbackend.controller;

import com.luckybag.luckybagbackend.domain.DTO.LuckyBagDTO;
import com.luckybag.luckybagbackend.domain.DTO.MemberDTO;
import com.luckybag.luckybagbackend.domain.DTO.NewLuckyBagDTO;
import com.luckybag.luckybagbackend.domain.DTO.UpdateLuckyBagDTO;
import com.luckybag.luckybagbackend.service.LuckyBagService;
import com.luckybag.luckybagbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ServiceController {//Service Test 용 Controller

    private final LuckyBagService luckyBagService;
    private final MemberService memberService;

// [Get] /luckybags -> 모두 조회
// [Get] /luckybags/{id} -> 복주머니 리스트에서 하나만 조회
// [Post] /members/{id}/{colorId}/luckybags -> 저장
// [patch] /luckybas/{id} -> 수정
// [delete] /luckybags/{id} -> 삭제

    // 복주머니 모두 조회
    // 6개 씩 페이징 예정
    @GetMapping("/luckybags")
    public Page<LuckyBagDTO> findAllWithDefault(@PageableDefault(size = 6) Pageable pageable) {
        Page<LuckyBagDTO> responses = luckyBagService.findAllWithPaging(pageable);
        log.info("responsesSize = {}", responses.getSize());
        return responses;
    }

    // 복주머니 하나만 조회
    @GetMapping("/luckybags/{id}")
    public ResponseEntity<LuckyBagDTO> findByMemberId(@PathVariable("id") Long id) {
        MemberDTO memberDTO = memberService.findById(id);
        return ResponseEntity.ok(luckyBagService.findByMemberId(id));
    }

    // 복주머니 저장
    @PostMapping("members/{id}/{colorId}/luckybags")
    public ResponseEntity<LuckyBagDTO> save(@PathVariable("id") Long id,@PathVariable("colorId")Long colorId,@RequestBody NewLuckyBagDTO newLuckyBagDTO) {

        // DTO를 파라미터로 받아 DTO를 리턴하는 saveEntity() 호출
        LuckyBagDTO luckyBagDTO = luckyBagService.saveEntity(id,colorId,newLuckyBagDTO);

        return ResponseEntity.ok(luckyBagDTO);
    }

    // 복주머니 삭제
    // id로 복주머니를 찾아서 그 복주머니를 디비에서 삭제
    // 삭제하려는 복주머니의 id로 삭제 요청이 들어오면 실행
    @DeleteMapping("/luckybags/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        luckyBagService.deleteByMemberId(id);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/luckybags/{id}")
    public ResponseEntity<Void> update(@PathVariable("id")Long id,@RequestBody UpdateLuckyBagDTO updateLuckyBagDTO) {
        luckyBagService.update(id, updateLuckyBagDTO);
        return ResponseEntity.ok(null);
    }



}