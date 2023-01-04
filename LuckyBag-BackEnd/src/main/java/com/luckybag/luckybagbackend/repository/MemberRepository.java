package com.luckybag.luckybagbackend.repository;

import com.luckybag.luckybagbackend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
