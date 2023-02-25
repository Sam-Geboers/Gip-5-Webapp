package be.ucll.gip5.repository;

import be.ucll.gip5.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Person, Long> {
}
