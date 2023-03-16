package com.luckybag.luckybagbackend.repository;

import com.luckybag.luckybagbackend.domain.LuckyBag;
import com.luckybag.luckybagbackend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LuckyBagRepository extends JpaRepository<LuckyBag, Long> {
    List<LuckyBag> findAllByMember(Member member);
    void deleteById(Long id);
}
