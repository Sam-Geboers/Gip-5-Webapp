package be.ucll.gip5.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Getter
    @Setter
    @Value("${spring.mail.host}")
    private String host;
    @Getter
    @Setter
    @Value("${spring.mail.user}")
    private String username;
    @Getter
    @Setter
    @Value("${spring.mail.password}")
    private String password;
    @Getter
    @Setter
    @Value("${spring.mail.port}")
    private int port;

}