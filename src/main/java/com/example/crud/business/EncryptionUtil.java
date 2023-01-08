package com.example.crud.business;


import com.example.crud.business.dto.SignupServiceCommand;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Component
public class EncryptionUtil {
    private static final Logger logger = Logger.getLogger(EncryptionUtil.class.getName());

    public SignupServiceCommand encryption(@RequestParam(required = false) SignupServiceCommand command) throws NoSuchAlgorithmException {

        try {
            byte[] hashValue = null;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // username으로 첫번째 암호화
            digest.update(command.getUsername().getBytes(StandardCharsets.UTF_8));

            // password로 두번째 암호화
            hashValue = digest.digest(command.getPassword().getBytes());

            command.setEncryptionPw(new String(Base64.encodeBase64(hashValue)));

        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.warning("command is empty.");
        }
        return command;
    }
}
