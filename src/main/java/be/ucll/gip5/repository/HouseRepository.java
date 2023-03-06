package be.ucll.gip5.repository;

import be.ucll.gip5.entity.House;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends CrudRepository<House, Long> {
    House findAllByHouseId(Long id);
    List<House> findAll();

    House findByName(String name);
}
