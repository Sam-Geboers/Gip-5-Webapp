package be.ucll.gip5.repository;

import be.ucll.gip5.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<User, Long> {
    User getAccountByUsername(String username);
//    Person findAllByAccountId(Long id);
    List<User> findAll();
}
