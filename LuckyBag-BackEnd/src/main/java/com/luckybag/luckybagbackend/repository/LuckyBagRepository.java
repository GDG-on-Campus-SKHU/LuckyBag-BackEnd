package com.luckybag.luckybagbackend.repository;

import com.luckybag.luckybagbackend.domain.LuckyBag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LuckyBagRepository extends JpaRepository<LuckyBag, Long> {
    Optional<LuckyBag> findByMemberId(Long memberId);
    void deleteByMemberId(Long id);
}
