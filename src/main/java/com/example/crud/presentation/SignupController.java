package com.example.crud.presentation;

import com.example.crud.business.SignupService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
public class SignupController {
    private final SignupService signupService;

    @PostMapping("/user/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody @Valid SignupRequestDto dto) {
        SignupService.SignupServiceCommand command = dto.toCommand();
        SignupService.SignupServiceInfo info = signupService.command(command);

        SignupResponseDto responseDto = new SignupResponseDto(info.getUsername());
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @AllArgsConstructor
    @Getter
    class SignupRequestDto {
        @Email
        private final String username;
        private final String password;

        private SignupService.SignupServiceCommand toCommand() {
            return SignupService.SignupServiceCommand.builder()
                    .username(this.username)
                    .password(this.password)
                    .build();
        }
    }

    @AllArgsConstructor
    @Getter
    class SignupResponseDto {
        private final String username;
    }
}
