package com.example.crud.business.dto;

import com.example.crud.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class SignupServiceCommand {
    private final String username;

    @Setter
    private String encryptionPw;
    private final String password;
    private final String phone;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.encryptionPw)
                .phone(this.phone)
                .build();
    }
}

