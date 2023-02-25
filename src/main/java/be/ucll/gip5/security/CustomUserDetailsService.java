package be.ucll.gip5.security;

import be.ucll.gip5.entity.Person;
import be.ucll.gip5.repository.AccountRepository;
import be.ucll.gip5.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person personByUsername = personRepository.getAccountByUsername(username);
        if(personByUsername!= null) {
            return new User(personByUsername.getUsername(), personByUsername.getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException(username);
    }
}

