package com.luckybag.luckybagbackend.service;

import com.luckybag.luckybagbackend.domain.DTO.MemberDTO;
import com.luckybag.luckybagbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDTO findById(Long id) {
        return memberRepository.findById(id).get().toDTO();
    }


}
