package ge.shota.blog.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GenerateUtil {

    private PasswordEncoder passwordEncoder;


    public String passwordEncode(String password) {
        return passwordEncoder.encode(password);
    }




    @Bean
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
