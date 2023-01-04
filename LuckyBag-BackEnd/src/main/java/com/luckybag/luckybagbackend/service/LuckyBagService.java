package com.luckybag.luckybagbackend.service;
import com.luckybag.luckybagbackend.domain.DTO.LuckyBagDTO;
import com.luckybag.luckybagbackend.domain.LuckyBag;
import com.luckybag.luckybagbackend.repository.LuckyBagRepository;
import com.luckybag.luckybagbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LuckyBagService {

    private final LuckyBagRepository luckyBagRepository;

    // 덕담 모두 조회
    @Transactional(readOnly = true)
    public List<LuckyBagDTO> findByAll() {
        List<LuckyBag> luckyBags = luckyBagRepository.findAll(); // 러키백 테이블에있는 러키백의 모든 레코드를 luckybag이라는 엔티티로 리스트에 넣어줌
        return luckyBags.stream() // luckyBags List객체의 스트림을 연다.
                .map(LuckyBag::toDto) // LuckyBag의 toDTO메소드로 LuckyBag클래스를 LuckyBagDTO로 변환한다.
                .collect(Collectors.toList());// 스트림을 List 변환하여 반환한다.
    }

    // 덕담 조회
    @Transactional(readOnly = true)
    public LuckyBagDTO findByMemberId(LuckyBagDTO dto) {
        Long memberId = dto.getMemberId();//가져온 dto에서 memberId를 가져온다.
        LuckyBag luckyBag = luckyBagRepository.findByMemberId(memberId);//memberId로 luckyBag을 찾기
        return luckyBag.toDto();
    }

    // 덕담 저장
    @Transactional
    public LuckyBagDTO save(LuckyBagDTO dto) {
        LuckyBag luckyBag = dto.toEntity();
        return luckyBagRepository.saveAndFlush(luckyBag).toDto();
    }

    // 덕담 수정
    @Transactional
    public LuckyBagDTO update(Long memberId, LuckyBagDTO dto) {
        LuckyBag luckyBag = luckyBagRepository.findByMemberId(memberId);
        luckyBag.setColorId(dto.getColorId());
        luckyBag.setComment(dto.getComment());
        return luckyBag.toDto();
    }

    // 덕담 삭제
    @Transactional
    public void deleteByMemberId(Long memberId) {
        luckyBagRepository.deleteByMemberId(memberId);
    }
}