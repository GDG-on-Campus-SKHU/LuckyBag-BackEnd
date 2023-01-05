package com.luckybag.luckybagbackend.service;

import com.luckybag.luckybagbackend.domain.Color;
import com.luckybag.luckybagbackend.domain.DTO.LuckyBagDTO;
import com.luckybag.luckybagbackend.domain.LuckyBag;
import com.luckybag.luckybagbackend.domain.Member;
import com.luckybag.luckybagbackend.repository.ColorRepository;
import com.luckybag.luckybagbackend.repository.LuckyBagRepository;
import com.luckybag.luckybagbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommonService {
    private final LuckyBagRepository luckyBagRepository;
    private final MemberRepository memberRepository;
    private final ColorRepository colorRepository;

    @Transactional
    public LuckyBagDTO save(LuckyBagDTO dto) {
        Member member = memberRepository.findById(dto.getMemberDTO().getId()).get();
        Color color = colorRepository.findById(dto.getColorDTO().getColorId()).get();
        log.info("member Name={}", dto.getColorDTO().getColorId());
        member.updateHasLuckyBag(true);
        LuckyBag luckyBag = LuckyBag.builder().member(member)
                .color(color)
                .comment(dto.getComment())
                .likeCount(dto.getLikeCount())
                .build();
        LuckyBag save = luckyBagRepository.save(luckyBag);
        log.info("------------");
        return save.toDto();
    }
}
