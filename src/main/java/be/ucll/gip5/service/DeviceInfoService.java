package be.ucll.gip5.service;

import be.ucll.gip5.dto.DeviceInfoDTO;
import be.ucll.gip5.entity.DeviceInfo;
import be.ucll.gip5.repository.DeviceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceInfoService {

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;
    @Autowired
    private DTOConverter dtoConverter;

    public void addDeviceInfo(DeviceInfoDTO dto){
        DeviceInfo deviceInfo = dtoConverter.DeviceInfoDTOToEntity(dto);
        deviceInfoRepository.save(deviceInfo);
    }
    public void editDeviceInfo(DeviceInfoDTO dto, Long id) throws Exception {
        DeviceInfo deviceInfo = deviceInfoRepository.findAllByDeviceInfoId(id);
        if (deviceInfo != null){
            deviceInfo = dtoConverter.DeviceInfoEntityToEntity(deviceInfo,dto);
            deviceInfoRepository.save(deviceInfo);
        }else {
            throw new ClassNotFoundException("DeviceInfo not found");
        }
    }
    public void deleteDeviceInfo(Long id) throws Exception {
        DeviceInfo deviceInfo = deviceInfoRepository.findAllByDeviceInfoId(id);
        if (deviceInfo != null){
            deviceInfoRepository.delete(deviceInfo);
        }else {
            throw new ClassNotFoundException("DeviceInfo not found");
        }
    }
    public DeviceInfoDTO getDeviceInfoById(Long id) throws Exception {
        DeviceInfo deviceInfo = deviceInfoRepository.findAllByDeviceInfoId(id);
        if (deviceInfo != null){
            return dtoConverter.DeviceInfoEntityToDTO(deviceInfo);
        }else {
            throw new ClassNotFoundException("DeviceInfo not found");
        }
    }
    public List<DeviceInfoDTO> getAllDeviceInfo(){
        List<DeviceInfo> deviceInfoList = deviceInfoRepository.findAll();
        List<DeviceInfoDTO> deviceInfoDTOS = new ArrayList<>();
        for (DeviceInfo d:deviceInfoList) {
            deviceInfoDTOS.add(dtoConverter.DeviceInfoEntityToDTO(d));
        }
        return deviceInfoDTOS;
    }
}
