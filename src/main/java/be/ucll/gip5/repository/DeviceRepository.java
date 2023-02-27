package be.ucll.gip5.repository;

import be.ucll.gip5.entity.Device;
import be.ucll.gip5.entity.DeviceInfo;
import be.ucll.gip5.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
    Device findAllById(Long id);
    List<Device> findAll();

}
