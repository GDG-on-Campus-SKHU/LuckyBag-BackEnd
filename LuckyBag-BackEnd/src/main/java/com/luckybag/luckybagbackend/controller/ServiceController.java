package com.luckybag.luckybagbackend.controller;

import com.luckybag.luckybagbackend.domain.DTO.ColorDTO;
import com.luckybag.luckybagbackend.domain.DTO.LuckyBagDTO;
import com.luckybag.luckybagbackend.domain.DTO.MemberDTO;
import com.luckybag.luckybagbackend.service.CommonService;
import com.luckybag.luckybagbackend.service.LuckyBagService;
import com.luckybag.luckybagbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ServiceController {//Service Test 용 Controller

    private final LuckyBagService luckyBagService;
    private final MemberService memberService;
    private final CommonService commonService;
    @GetMapping("/findAll")
    public ResponseEntity<List<LuckyBagDTO>> findAll() {
        return ResponseEntity.ok(luckyBagService.findByAll());
    }
    @GetMapping("/findByMemberId")
    public ResponseEntity<LuckyBagDTO> findByMemberId() {
        MemberDTO memberDTO = memberService.findById(1L);
        return ResponseEntity.ok(luckyBagService.findByMemberId(memberDTO));
    }

    @GetMapping("/save/{id}")
    public ResponseEntity save(@PathVariable Long id) {
        ColorDTO colorDTO = ColorDTO.builder().colorId(1L).build();
        MemberDTO memberDTO = memberService.findById(id);
        if (memberDTO.isHasLuckyBag() == true) {//덕담을 썼다면 406에러
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("이미 덕담을 작성하였습니다.");
        }
        LuckyBagDTO luckyBagDTO = LuckyBagDTO.builder().memberDTO(memberDTO).colorDTO(colorDTO).comment("새해입니다.").likeCount(3L).build();
        return ResponseEntity.ok(commonService.save(luckyBagDTO));
    }
    @GetMapping("/update")
    public ResponseEntity update() {
        MemberDTO memberDTO = memberService.findById(1L);
        ColorDTO colorDTO = ColorDTO.builder().colorId(2L).build();
        LuckyBagDTO luckyBagDTO = LuckyBagDTO.builder().memberDTO(memberDTO).colorDTO(colorDTO).comment("새해입니다.").likeCount(3L).build();
        return ResponseEntity.ok(luckyBagService.update(luckyBagDTO));
    }

    @GetMapping("/delete")
    public void delete() {
        luckyBagService.deleteByMemberId(2L);
    }


}
