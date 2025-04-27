package mang.church.infra.output.persistence.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion.$2B;

@UtilityClass
public class Encrypter {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder($2B, 12);

    public static String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
