package com.luckybag.luckybagbackend.domain;

import com.luckybag.luckybagbackend.domain.DTO.MemberDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String memberId; // 자바용

    @Column(name = "user_password", nullable = false)
    private String memberPassword;

    @Column(name = "member_name")
    private String memberName;

    @OneToOne(mappedBy = "member")
    private LuckyBag luckyBag;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column
    @Convert(converter = BooleanToYNConverter.class)
    private boolean hasLuckyBag;
    public void updateHasLuckyBag(boolean hasLuckyBag) {
        this.hasLuckyBag = hasLuckyBag;
    }



    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .id(id)
                .nickname(nickname)
                .hasLuckyBag(hasLuckyBag)
                .build();
    }

}