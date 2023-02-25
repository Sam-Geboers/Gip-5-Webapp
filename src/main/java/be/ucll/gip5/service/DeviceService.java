package be.ucll.gip5.service;

import be.ucll.gip5.dto.DeviceDTO;
import be.ucll.gip5.entity.Device;
import be.ucll.gip5.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public void addDevice(DeviceDTO deviceDTO){

    }

    public void editDevice(Long id, DeviceDTO deviceDTO) throws Exception{
        Device device = deviceRepository.findAllById(id);
        if (device != null) {
            device.setName(deviceDTO.getName());
//            device.setSpace(deviceDTO.getSpace);
            deviceRepository.save(device);
        }else{
            throw new ClassNotFoundException("Device not found");
        }
    }

    public void deleteDevice(Long id) throws Exception{
        Device device = deviceRepository.findAllById(id);
        if (device != null) {
            deviceRepository.delete(device);
        }else {
            throw new ClassNotFoundException("Device not found");
        }
    }

    public void getDevice(Long id) throws Exception{
        Device device = deviceRepository.findAllById(id);
        if (device != null) {

        }else {
            throw new ClassNotFoundException("Device not found");
        }
    }

    public void getALlDevices(){
//        List<Device> devices = deviceRepository.findAll();

    }
}
