package com.luckybag.luckybagbackend.service;

import com.luckybag.luckybagbackend.domain.DTO.*;
import com.luckybag.luckybagbackend.domain.LuckyBag;
import com.luckybag.luckybagbackend.domain.Member;
import com.luckybag.luckybagbackend.repository.LuckyBagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
@Slf4j
@RequiredArgsConstructor
public class LuckyBagService {

    private final LuckyBagRepository luckyBagRepository;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public Page<LuckyBagDTO> findAllWithPaging(Pageable pageable) {
        return luckyBagRepository.findAll(pageable).map(luckyBag ->
                LuckyBagDTO.builder()
                        .luckyBagId(luckyBag.getId())
                        .comment(luckyBag.getComment())
                        .color(luckyBag.getColor())
                        .memberDTO(luckyBag.getMember().toDTO())
                        .build());
    }


    @Transactional
    public LuckyBagDTO saveEntity(Long MemberId,NewLuckyBagDTO newLuckyBagDTO) {
        Member member = memberService.findEntityById(MemberId);

        LuckyBag luckyBag = LuckyBag.builder()
                .color(newLuckyBagDTO.getColor())
                .comment(newLuckyBagDTO.getComment())
                .member(member)
                .build();

        LuckyBag savedLuckyBag = luckyBagRepository.save(luckyBag);

        Member savedMember = savedLuckyBag.getMember();

        member.updateHasLuckyBag(true);

        MemberDTO memberDto = MemberDTO.builder()
                .id(savedMember.getId())
                .nickname(savedMember.getNickname())
                .hasLuckyBag(savedMember.isHasLuckyBag())
                .build();

        LuckyBagDTO luckyBagDTO = LuckyBagDTO.builder()
                .luckyBagId(savedLuckyBag.getId())
                .color(savedLuckyBag.getColor())
                .comment(savedLuckyBag.getComment())
                .memberDTO(memberDto)
                .build();

        return luckyBagDTO;
    }

    @Transactional
    public void deleteByMemberId(Long memberId) {
        luckyBagRepository.deleteByMemberId(memberId);
    }

    @Transactional
    public LuckyBagDTO update(Long id, UpdateLuckyBagDTO updateluckyBagDTO) {
        LuckyBag luckyBag = luckyBagRepository.findByMemberId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "작성한 덕담이 존재하지 않습니다."));;
        return luckyBag.update(updateluckyBagDTO);
    }

    @Transactional(readOnly = true)
    public FindLuckyBagDTO findByLuckyBag(Long id) {
        LuckyBag luckyBag = luckyBagRepository.findById(id).get();
        return FindLuckyBagDTO.builder()
                .comment(luckyBag.getComment())
                .luckyBagId(luckyBag.getId()).build();
    }
}