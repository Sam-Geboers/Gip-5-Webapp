package be.ucll.gip5.repository;

import be.ucll.gip5.entity.Space;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends CrudRepository<Space, Long> {
    Space findAllBySpaceId(Long id);
    List<Space> findAll();
}
