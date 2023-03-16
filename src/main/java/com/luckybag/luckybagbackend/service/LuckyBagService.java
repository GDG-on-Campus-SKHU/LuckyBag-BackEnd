package com.luckybag.luckybagbackend.service;

import com.luckybag.luckybagbackend.domain.DTO.*;
import com.luckybag.luckybagbackend.domain.LuckyBag;
import com.luckybag.luckybagbackend.domain.Member;
import com.luckybag.luckybagbackend.repository.LuckyBagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LuckyBagService {

    private final LuckyBagRepository luckyBagRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public Page<LuckyBagResponseDTO> findAll(Pageable pageable) {
        return luckyBagRepository.findAll(pageable).map(LuckyBag::toDTO);
    }


    @Transactional
    public LuckyBagResponseDTO save(LuckyBagRequestDTO luckyBagRequestDTO, Principal principal) {
        Member member = authService.findByUsername(principal.getName());

        LuckyBag luckyBag = LuckyBag.builder()
                .color(luckyBagRequestDTO.getColor())
                .comment(luckyBagRequestDTO.getComment())
                .member(member)
                .build();

        luckyBagRepository.save(luckyBag);

        member.updateHasLuckyBag(true);

        return luckyBag.toDTO();
    }

    @Transactional
    public void deleteById(Long id) {
        luckyBagRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, LuckyBagUpdateDTO updateluckyBagUpdateDTO) {
        LuckyBag luckyBag = luckyBagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "작성한 덕담이 존재하지 않습니다."));

        luckyBag.update(updateluckyBagUpdateDTO.getComment());
    }

    @Transactional(readOnly = true)
    public LuckyBagResponseDTO findById(Long id) {
        LuckyBag luckyBag = luckyBagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID에 맞는 복주머니가 존재하지 않습니다."));

        return luckyBag.toDTO();
    }

    public List<LuckyBagResponseDTO> findAllByMember(Principal principal) {
        Member member = authService.findByUsername(principal.getName());

        List<LuckyBagResponseDTO> luckyBags = luckyBagRepository.findAllByMember(member)
                .stream()
                .map(LuckyBag::toDTO)
                .collect(Collectors.toList());

        return luckyBags;
    }
}