package com.luckybag.luckybagbackend.service;


import com.luckybag.luckybagbackend.domain.DTO.MemberDTO;
import com.luckybag.luckybagbackend.domain.Member;
import com.luckybag.luckybagbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDTO findById(Long id) {
        return memberRepository.findById(id).get().toDTO();
    }

    public Member findEntityById(Long id){
        return memberRepository.findById(id).get();
    }

    public Member save(Member member){
        return memberRepository.save(member);
    }

}
