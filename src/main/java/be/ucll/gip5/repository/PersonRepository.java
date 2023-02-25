package be.ucll.gip5.repository;

import be.ucll.gip5.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.CachedRowSet;

@Repository
public interface PersonRepository extends CrudRepository {
    Person getAccountByUsername(String username);
}

