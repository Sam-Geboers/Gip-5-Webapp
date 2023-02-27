package be.ucll.gip5.repository;

import be.ucll.gip5.entity.DeviceInfo;
import be.ucll.gip5.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceInfoRepository extends CrudRepository<DeviceInfo, Long> {
    DeviceInfo findAllById(Long id);
    List<DeviceInfo> findAll();
}
