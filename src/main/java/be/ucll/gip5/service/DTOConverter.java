package be.ucll.gip5.service;

import be.ucll.gip5.dto.*;
import be.ucll.gip5.entity.*;
import org.springframework.stereotype.Service;

@Service
public class DTOConverter {
    /***Device***/
    public Device DeviceDTOToEntity(DeviceDTO dto){
        Device device = new Device();
        device.setName(dto.getName());
        return device;
    }
    public DeviceDTO DeviceEntityToDTO(Device device){
        DeviceDTO dto = new DeviceDTO();
        dto.setName(device.getName());
        return dto;
    }
    /***DeviceInfo***/
    public DeviceInfo DeviceInfoDTOToEntity(DeviceInfoDTO dto){
        DeviceInfo info = new DeviceInfo();
        info.setConsumption(dto.getConsumption());
        info.setDeviceStatus(dto.isDeviceStatus());
        info.setDeviceInformation(dto.getDeviceInformation());
        info.setTypeOfDevice(dto.getTypeOfDevice());
        return info;
    }
    public DeviceInfoDTO DeviceInfoEntityToDTO(DeviceInfo info){
        DeviceInfoDTO dto = new DeviceInfoDTO();
        dto.setConsumption(info.getConsumption());
        dto.setDeviceStatus(info.isDeviceStatus());
        dto.setDeviceInformation(info.getDeviceInformation());
        dto.setTypeOfDevice(info.getTypeOfDevice());
        return dto;
    }
    public DeviceInfo DeviceInfoEntityToEntity(DeviceInfo info, DeviceInfoDTO dto){
        info.setTypeOfDevice(dto.getTypeOfDevice());
        info.setConsumption(dto.getConsumption());
        info.setDeviceStatus(dto.isDeviceStatus());
        info.setDeviceInformation(dto.getDeviceInformation());
        return info;
    }

    public Device deviceEntityToEntity(Device device, DeviceDTO dto){
        device.setName(dto.getName());
        //werkt nog niet
//        device.setSpace(dto.get);
//        device.setDeviceInfo(dto.get);
        return device;
    }
    /***House***/
    public House HouseDTOToEntity(HouseDTO dto){
        House house = new House();
        house.setName(dto.getName());
        house.setAddress(dto.getAddress());
        return house;
    }
    public HouseDTO HouseEntityToDTO(House house){
        HouseDTO dto = new HouseDTO();
        dto.setName(house.getName());
        dto.setAddress(house.getAddress());
        return dto;
    }

    public House HouseEntityToEntity(House house, HouseDTO dto){
        house.setName(dto.getName());
        house.setAddress(dto.getAddress());
        return house;
    }

    /***Person***/
    public User UserDTOToEntity(UserDTO dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRole());
        return user;
    }
    public UserDTO UserEntityToDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRoles());
        return dto;
    }
    public User UserEntityToEntity(User user, UserDTO dto){
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setRoles(dto.getRole());
        return user;
    }
    /***Space***/
    public Space SpaceDTOToEntity(SpaceDTO dto){
        Space space = new Space();
        space.setName(dto.getName());
        space.setDescription(dto.getDescription());
        return space;
    }
    public SpaceDTO SpaceEntityToDTO(Space space){
        SpaceDTO dto = new SpaceDTO();
        dto.setName(space.getName());
        dto.setDescription(space.getDescription());
        return dto;
    }

    public Space SpaceEntityToEntity(Space space, SpaceDTO dto){
        space.setName(dto.getName());
        space.setDescription(dto.getDescription());
        return space;
    }
}
