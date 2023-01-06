package com.luckybag.luckybagbackend.service;
import com.luckybag.luckybagbackend.domain.DTO.LuckyBagDTO;
import com.luckybag.luckybagbackend.domain.DTO.MemberDTO;
import com.luckybag.luckybagbackend.domain.DTO.NewLuckyBagDTO;
import com.luckybag.luckybagbackend.domain.LuckyBag;
import com.luckybag.luckybagbackend.domain.Member;
import com.luckybag.luckybagbackend.repository.LuckyBagRepository;
import com.luckybag.luckybagbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LuckyBagService {

    private final LuckyBagRepository luckyBagRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    // 덕담 모두 조회
    @Transactional(readOnly = true)
    public List<LuckyBagDTO> findByAll() {
        List<LuckyBag> luckyBags = luckyBagRepository.findAll();
        return luckyBags.stream()
                .map(LuckyBag::toDto)
                .collect(Collectors.toList());
    }

    // 덕담 조회
    @Transactional(readOnly = true)
    public LuckyBagDTO findByMemberId(MemberDTO memberDTO) {
        Long memberId = memberDTO.getId();
        LuckyBag luckyBag = luckyBagRepository.findByMemberId(memberId);
        return luckyBag.toDto();
    }

    // 덕담 저장
    @Transactional
    public LuckyBagDTO saveEntity(NewLuckyBagDTO newLuckyBagDTO) {
        Member member = memberService.findEntityById(newLuckyBagDTO.getMemberId());
        // 1. 읽어들여온 DTO 데이터를 바탕으로 Entity 클래스의 객체를 생성한다.
        LuckyBag luckyBag = LuckyBag.builder()
                .color_name(newLuckyBagDTO.getColorName())
                .comment(newLuckyBagDTO.getComment())
                .likeCount(0L)
                .member(member)
                .build();

        // 2. 생성한 Entity 객체를 저장한다.
        LuckyBag savedLuckyBag = luckyBagRepository.save(luckyBag);
        Member savedMember = savedLuckyBag.getMember();
        member.updateHasLuckyBag(true);

        // 3. dto return
        MemberDTO memberDto = MemberDTO.builder()
                .id(savedMember.getId())
                .nickname(savedMember.getNickname())
                .hasLuckyBag(savedMember.isHasLuckyBag())
                .build();

        LuckyBagDTO luckyBagDTO = LuckyBagDTO.builder()
                .luckyBagId(savedLuckyBag.getId())
                .colorName(savedLuckyBag.getColor_name())
                .comment(savedLuckyBag.getComment())
                .memberDTO(memberDto)
                .build();

        return luckyBagDTO;
    }

    // 덕담 삭제
    @Transactional
    public void deleteByMemberId(Long memberId) {
        luckyBagRepository.deleteByMemberId(memberId);
    }
}