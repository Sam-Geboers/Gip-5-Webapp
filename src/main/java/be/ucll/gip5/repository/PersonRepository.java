package be.ucll.gip5.repository;

import be.ucll.gip5.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
    Person getAccountByUsername(String username);
    Person findAllByPersonId(Long id);
    List<Person> findAll();
}

