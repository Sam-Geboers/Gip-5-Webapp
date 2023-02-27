package be.ucll.gip5.repository;

import be.ucll.gip5.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User getAccountByUsername(String username);
    User findAllByPersonId(Long id);
    List<User> findAll();
}

