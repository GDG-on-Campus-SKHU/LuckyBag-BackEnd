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

    // 아이디
    @Column(name = "user_id", nullable = false)
    private String memberId;

    // 비밀번호
    @Column(name = "user_password", nullable = false)
    private String memberPassword;

    @OneToOne(mappedBy = "member")
    private LuckyBag luckyBag;

    // 닉네임
    @Column(name = "nickname", nullable = false)
    private String nickname;

    // 복주머니 생성 유무
    @Column
    @Convert(converter = BooleanToYNConverter.class)
    private boolean hasLuckyBag;
    public void updateHasLuckyBag(boolean hasLuckyBag) {
        this.hasLuckyBag = hasLuckyBag;
    }

    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .id(id)
                .hasLuckyBag(hasLuckyBag)
                .nickname(nickname)
                .build();
    }

}