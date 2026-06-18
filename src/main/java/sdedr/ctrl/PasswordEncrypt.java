package sdedr.ctrl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncrypt {

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder(12);

    public String encryptPassword(String password) {
        return encoder.encode(password);
    }

    public boolean checkPassword(String password, String hash) {
        return encoder.matches(password, hash);
    }
}