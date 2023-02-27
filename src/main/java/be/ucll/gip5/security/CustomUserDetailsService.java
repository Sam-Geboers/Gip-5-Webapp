package be.ucll.gip5.security;

import be.ucll.gip5.entity.Person;
import be.ucll.gip5.repository.AccountRepository;
import be.ucll.gip5.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person personByUsername = personRepository.getAccountByUsername(username);
        Person person = personRepository.getAccountByUsername(username);
        System.out.println(jwtUtil.generate(person));
        if(personByUsername!= null) {
            return new User(personByUsername.getUsername(), personByUsername.getPassword() , new ArrayList<>());
        }
        throw new UsernameNotFoundException(username);
    }

    //register

}

//new BCryptPasswordEncoder().encode(personByUsername.getPassword())