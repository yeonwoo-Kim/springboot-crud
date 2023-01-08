package com.example.crud.presentation;

import com.example.crud.business.SignupService;
import com.example.crud.business.dto.SignupServiceCommand;
import com.example.crud.business.dto.SignupServiceInfo;
import com.example.crud.presentation.dto.SignupRequestDto;
import com.example.crud.presentation.dto.SignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SignupController {
    private final SignupService signupService;

    @PostMapping("/user/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody @Valid SignupRequestDto dto) throws Exception {
        SignupServiceCommand command = dto.toCommand(); // DTO를 DB에 적용하기 위한 command 객체로 바꿈
        SignupServiceInfo info = signupService.command(command); // command하기 위한 비즈니스 로직 수행 후 정보를 보여줄 info객체 받기

        SignupResponseDto responseDto = new SignupResponseDto(info.getUsername(), info.getPhone());
        return new ResponseEntity(responseDto, HttpStatus.CREATED); // 리소스가 생성됨을 알리는 HTTP 상태코드와 리소스 정보를 응답하는 responseDTO return
    }
}
