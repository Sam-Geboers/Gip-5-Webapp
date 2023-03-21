package be.ucll.gip5.service;

import be.ucll.gip5.dto.DeviceDTO;
import be.ucll.gip5.entity.Device;
import be.ucll.gip5.entity.DeviceInfo;
import be.ucll.gip5.entity.Space;
import be.ucll.gip5.repository.DeviceInfoRepository;
import be.ucll.gip5.repository.DeviceRepository;
import be.ucll.gip5.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
    private DTOConverter dtoConverter;
    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    public void addDevice(DeviceDTO deviceDTO, Long spaceId) throws Exception{
        Device device = dtoConverter.DeviceDTOToEntity(deviceDTO);
        Space space = spaceRepository.findAllBySpaceId(spaceId);

        if (space == null) throw new ClassNotFoundException("Space not found.");

        device.setSpace(space);
        space.getDeviceList().add(device);

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

    public void addDeviceInfoToDevice(Long deviceId, Long deviceInfoId) throws Exception{
        Device device = deviceRepository.findAllByDeviceId(deviceId);
        DeviceInfo deviceInfo = deviceInfoRepository.findAllByDeviceInfoId(deviceInfoId);

        if(device == null){
            throw new ClassNotFoundException("Device not found");
        }
        if(deviceInfo == null){
            throw new ClassNotFoundException("DeviceInfo not found");
        }

        device.setDeviceInfo(deviceInfo);
        deviceRepository.save(device);
    }

}
