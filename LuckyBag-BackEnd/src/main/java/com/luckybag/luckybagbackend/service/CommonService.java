package com.luckybag.luckybagbackend.service;

import com.luckybag.luckybagbackend.domain.DTO.LuckyBagDTO;
import com.luckybag.luckybagbackend.domain.LuckyBag;
import com.luckybag.luckybagbackend.domain.Member;
import com.luckybag.luckybagbackend.repository.LuckyBagRepository;
import com.luckybag.luckybagbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommonService {
    private final LuckyBagRepository luckyBagRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public LuckyBagDTO save(LuckyBagDTO dto) {
        Member member = memberRepository.findById(dto.getMemberDTO().getId()).get();
        member.updateHasLuckyBag(true);
        LuckyBag luckyBag = LuckyBag.builder()
                .member(member)
                .colorId(dto.getColorDTO().getColorId())
                .comment(dto.getComment())
                .memberId(dto.getMemberDTO().getId())
                .likeCount(dto.getLikeCount())
                .build();
        return luckyBagRepository.saveAndFlush(luckyBag).toDto();
    }
}
