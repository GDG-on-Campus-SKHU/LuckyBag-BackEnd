package com.luckybag.luckybagbackend.repository;

import com.luckybag.luckybagbackend.domain.LuckyBag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LuckyBagRepository extends JpaRepository<LuckyBag, Long> {
    LuckyBag findByMemberId(Long memberId);
    void deleteByMemberId(Long id);
}
