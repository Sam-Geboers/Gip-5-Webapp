package be.ucll.gip5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Zorgt ervoor dat jouw configuratie voorrang heeft op de default manier!
//@EnableGlobalMethodSecurity(
//        prePostEnabled = false, securedEnabled = false, jsr250Enabled = true)

public class WebSecurity {
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
            throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http // disabling csrf since we won't use form login
//                .csrf().disable()
                // giving every permission to every request for /login endpoint
//                .authorizeRequests().antMatchers("/",
//                        "/login",
//                        "/login/register",
//                        "/Spot").permitAll()
                // for everything else, the user has to be authenticated
//                .anyRequest().authenticated()
                // setting stateless session, because we choose to implement Rest API
//                .and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // adding the custom filter before UsernamePasswordAuthenticationFilter in the filter chain
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.userDetailsService(customUserDetailsService);
        return http.build();
    }
}
