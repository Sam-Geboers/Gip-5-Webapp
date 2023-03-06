package be.ucll.gip5.security2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //endpoints Create, Delete, (update)
    private static final String[] SECURED_URLS = {"/spaces/**"};
    //endpoints READ (update)
    private static final String[] UNSECURED_URLS = {"/user/**",
                                                    "/swagger-ui/",
                                                    "/swagger-ui/**",
                                                    "/swagger-resources/**",
                                                    "/spaces/get-space/{id}"};

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(UNSECURED_URLS)
                .permitAll().and()
                .authorizeHttpRequests().antMatchers(SECURED_URLS)
                .hasAuthority("ADMIN").anyRequest()
                .authenticated().and().httpBasic().and().build();
    }
}
