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
        Member member1 = Member.builder()
                .memberId("test123")
                .memberPassword("test123")
                .nickname("자경공주")
                .hasLuckyBag(false)
                .build();
        Member member2 = Member.builder()
                .memberId("test2")
                .memberPassword("test2")
                .nickname("자경공주2")
                .hasLuckyBag(false)
                .build();
        Member member3 = Member.builder()
                .memberId("test3")
                .memberPassword("test3")
                .nickname("자경공주3")
                .hasLuckyBag(false)
                .build();
        Member member4 = Member.builder()
                .memberId("test4")
                .memberPassword("test4")
                .nickname("자경공주4")
                .hasLuckyBag(false)
                .build();
        Member member5 = Member.builder()
                .memberId("test5")
                .memberPassword("test5")
                .nickname("자경공주5")
                .hasLuckyBag(false)
                .build();
        Member member6 = Member.builder()
                .memberId("test6")
                .memberPassword("test6")
                .nickname("자경공주6")
                .hasLuckyBag(false)
                .build();
        memberService.save(member1);
        memberService.save(member2);
        memberService.save(member3);
        memberService.save(member4);
        memberService.save(member5);
        memberService.save(member6);
    }
}
