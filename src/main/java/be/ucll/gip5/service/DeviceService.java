package be.ucll.gip5.service;

import be.ucll.gip5.dto.DeviceDTO;
import be.ucll.gip5.entity.Device;
import be.ucll.gip5.entity.House;
import be.ucll.gip5.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DTOConverter dtoConverter;

    public void addDevice(DeviceDTO deviceDTO){
        Device device = dtoConverter.DeviceDTOToEntity(deviceDTO);
        deviceRepository.save(device);
    }

    public void editDevice(Long id, DeviceDTO deviceDTO) throws Exception{
        Device device = deviceRepository.findAllByDeviceId(id);
        if (device == null) {
            throw new ClassNotFoundException("Device not found");
        }

        device = dtoConverter.deviceEntityToEntity(device, deviceDTO);
        deviceRepository.save(device);
    }

    public void deleteDevice(Long id) throws Exception{
        Device device = deviceRepository.findAllByDeviceId(id);
        if (device != null) {
            deviceRepository.delete(device);
        }else {
            throw new ClassNotFoundException("Device not found");
        }
    }

    public DeviceDTO getDevice(Long id) throws Exception{
        Device device = deviceRepository.findAllByDeviceId(id);
        if (device != null) {
            return dtoConverter.DeviceEntityToDTO(device);
        }else {
            throw new ClassNotFoundException("Device not found");
        }
    }

    public List<DeviceDTO> getALlDevices(){
        List<Device> deviceList = deviceRepository.findAll();
        List<DeviceDTO> deviceDTOS = new ArrayList<>();
        for (Device d:deviceList) {
            deviceDTOS.add(dtoConverter.DeviceEntityToDTO(d));
        }
        return deviceDTOS;
    }
}
