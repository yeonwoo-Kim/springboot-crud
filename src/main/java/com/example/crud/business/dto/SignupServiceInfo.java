package com.example.crud.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SignupServiceInfo {
    private final String username;
    private final String phone;

    /**
     * @AllArgsConstructor와 동일
     * public SignupServiceInfo(String username, String password) {
     *     this.username = username;
     * }
     */
}
