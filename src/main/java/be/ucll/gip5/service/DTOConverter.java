package be.ucll.gip5.service;

import be.ucll.gip5.dto.*;
import be.ucll.gip5.entity.*;
import org.springframework.stereotype.Service;

@Service
public class DTOConverter {
    /***Device***/
    public Device DeviceDTOToEntity(DeviceDTO dto){
        Device device = new Device();
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
        return info;
    }
    public DeviceInfoDTO DeviceEntityToDTO(DeviceInfo info){
        DeviceInfoDTO dto = new DeviceInfoDTO();
        dto.setConsumption(info.getConsumption());
        dto.setDeviceStatus(info.isDeviceStatus());
        dto.setDeviceInformation(info.getDeviceInformation());
        dto.setTypeOfDevice(info.getTypeOfDevice());
        return dto;
    }
    /***House***/
    public House HouseDTOToEntity(HouseDTO dto){
        House house = new House();
        return house;
    }
    public HouseDTO HouseEntityToDTO(House house){
        HouseDTO dto = new HouseDTO();
        dto.setName(house.getName());
        dto.setAddress(house.getAddress());
        return dto;
    }
    /***Person***/
    public Person PersonDTOToEntity(PersonDTO dto){
        Person person = new Person();
        return person;
    }
    public PersonDTO PersonEntityToDTO(Person person){
        PersonDTO dto = new PersonDTO();
        dto.setEmail(person.getEmail());
        dto.setRole(person.getRole());
        dto.setPassword(person.getPassword());
        dto.setUsername(person.getUsername());
        return dto;
    }
    /***Space***/
    public Space SpaceDTOToEntity(SpaceDTO dto){
        Space space = new Space();
        return space;
    }
    public SpaceDTO SpaceEntityToDTO(Space space){
        SpaceDTO dto = new SpaceDTO();
        dto.setName(space.getName());
        dto.setDescription(space.getDescription());
        return dto;
    }
}
