package com.luckybag.luckybagbackend.repository;

import com.luckybag.luckybagbackend.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {

    Optional<Color> findById(Long id);
}
