package be.ucll.gip5.security;

import be.ucll.gip5.entity.User;
import be.ucll.gip5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByUsername = userRepository.getAccountByUsername(username);
        User user = userRepository.getAccountByUsername(username);
        System.out.println(jwtUtil.generate(user));
        if(userByUsername != null) {
            return new org.springframework.security.core.userdetails.User(userByUsername.getUsername(), userByUsername.getPassword() , new ArrayList<>());
        }
        throw new UsernameNotFoundException(username);
    }

    //register

}

//new BCryptPasswordEncoder().encode(personByUsername.getPassword())