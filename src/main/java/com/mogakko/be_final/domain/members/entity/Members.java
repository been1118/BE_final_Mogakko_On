package com.mogakko.be_final.domain.members.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private Long kakaoId;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAgreed;

    @Column(nullable = false)
    private boolean emailAuth;

    public Members( String email, String nickname, String password, boolean isAgreed, boolean emailAuth){
        this.email = email;
        this.password = password;
        this.nickName = nickname;
        this.isAgreed = isAgreed;
        this.emailAuth = emailAuth;
    }

    public void emailVerifiedSuccess() {
        this.emailAuth = true;
    }

    public Members kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }



}
