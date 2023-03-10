package com.example.crud.business;

import com.example.crud.business.dto.SignupServiceCommand;
import com.example.crud.business.dto.SignupServiceInfo;
import com.example.crud.domain.User;
import com.example.crud.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserRepository userRepository;
    private final EncryptionUtil encryptionUtil;

    @Transactional
    public SignupServiceInfo command(SignupServiceCommand command) throws NoSuchAlgorithmException {
        // username으로 가입된 회원이 있는지 확인하고, 중복된다면 예외 throw
        Boolean isExists = userRepository.existsByUsername(command.getUsername());
        if (isExists) throw new RuntimeException(command.getUsername());

        // 비밀번호 암호화처리
        // 중복된 username이 아니라면 회원가입
        User savedUser = userRepository.save((encryptionUtil.encryption(command)).toEntity()); // SignupServiceCommand를 User로 변환해서 DB 저장

        // 회원가입된 엔티티를 DTO로 변환해서 return
        return new SignupServiceInfo(savedUser.getUsername(), savedUser.getPhone());
    }

}
