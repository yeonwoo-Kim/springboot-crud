package com.example.crud.presentation.dto;

import com.example.crud.business.dto.SignupServiceCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    @Email
    private String username;
    private String password;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private String phone;

    public SignupServiceCommand toCommand() {
        return new SignupServiceCommand(username, password, phone);
    }
}
