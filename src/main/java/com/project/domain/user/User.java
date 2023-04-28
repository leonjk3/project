package com.project.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Unique
    private Long userId;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private SexType sex;

    @Builder
    public User(String loginId, String userName, String password, String email, String phone, String address, SexType sex) {
        this.loginId = loginId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.sex = sex;
    }
}
