package be.ucll.gip5.repository;

import be.ucll.gip5.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findAllByUserId(Long id);
    List<User> findAll();

    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    boolean existsByEmail(String email);
}

