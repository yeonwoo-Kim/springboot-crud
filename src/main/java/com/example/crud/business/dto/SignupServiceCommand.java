package com.example.crud.business.dto;

import com.example.crud.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SignupServiceCommand {
    private final String username;
    private final String password;
    private final String phone;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .phone(this.phone)
                .build();
    }
}

