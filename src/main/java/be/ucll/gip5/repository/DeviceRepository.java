package be.ucll.gip5.repository;

import be.ucll.gip5.entity.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository {
    Device findAllById(Long id);
}
