package be.ucll.gip5.repository;

import be.ucll.gip5.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Person, Long> {
    Person getAccountByUsername(String username);
//    Person findAllByAccountId(Long id);
    List<Person> findAll();
}
