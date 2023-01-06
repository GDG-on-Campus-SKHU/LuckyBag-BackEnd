package com.luckybag.luckybagbackend.config.init;

import com.luckybag.luckybagbackend.domain.Member;
import com.luckybag.luckybagbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MemberInit {

    private final MemberService memberService;

    @PostConstruct
    public void memberInit(){
        Member member = Member.builder()
                .memberId("test123")
                .memberPassword("test123")
                .nickname("자경공주")
                .hasLuckyBag(false)
                .build();
        memberService.save(member);
    }
}
