package com.luckybag.luckybagbackend.domain;

import com.luckybag.luckybagbackend.domain.DTO.MemberDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false) // sql 등록용(데베로 볼땐 이걸로)
    private String userId; // 자바용

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .userId(userId)
                .nickname(nickname)
                .build();
    }
}