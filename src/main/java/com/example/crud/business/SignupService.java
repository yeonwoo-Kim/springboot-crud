package com.example.crud.business;

import com.example.crud.domain.User;
import com.example.crud.persistence.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserRepository userRepository;

    @Transactional
    public SignupServiceInfo command(SignupServiceCommand command) {
        // username으로 가입된 회원이 있는지 확인하고, 중복된다면 예외 throw
        Boolean isExists = userRepository.existsByUsername(command.getUsername());
        if (isExists) throw new RuntimeException(command.getUsername());

        // 중복된 username이 아니라면 회원가입
        User savedUser = userRepository.save(command.toEntity());

        // 회원가입된 엔티티를 dto로 변환해서 return
        return SignupServiceInfo.from(savedUser);
    }

    @AllArgsConstructor
    @Getter
    class SignupServiceCommand {
        private final String username;
        private final String password;

        private User toEntity() {
            return User.builder()
                    .username(this.username)
                    .password(this.password)
                    .build();
        }
    }

    @AllArgsConstructor
    @Builder
    @Getter
    class SignupServiceInfo {
        private final String username;
        private final String password;

        /**
         * @AllArgsConstructor와 동일
         * public SignupServiceInfo(String username, String password) {
         *     this.username = username;
         *     this.password = password;
         * }
         */

        private static SignupServiceInfo from(User user) {
            return SignupServiceInfo.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();
        }
    }
}
