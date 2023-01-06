package com.luckybag.luckybagbackend.repository;

import com.luckybag.luckybagbackend.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Long> {
}
