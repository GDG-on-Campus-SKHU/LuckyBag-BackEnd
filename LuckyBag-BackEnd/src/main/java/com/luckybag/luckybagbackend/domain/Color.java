package com.luckybag.luckybagbackend.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "color")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String colorName;

}
