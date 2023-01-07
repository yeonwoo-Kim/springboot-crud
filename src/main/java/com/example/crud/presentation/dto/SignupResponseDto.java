package com.example.crud.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignupResponseDto {
    private final String username;
    private final String phone;
}
