package be.ucll.gip5.service;

import be.ucll.gip5.dto.SpaceDTO;
import be.ucll.gip5.dto.SpaceWithListDTO;
import be.ucll.gip5.entity.Device;
import be.ucll.gip5.entity.House;
import be.ucll.gip5.entity.Space;
import be.ucll.gip5.repository.HouseRepository;
import be.ucll.gip5.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpaceService {
    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private DTOConverter dtoConverter;

    public void addSpace(SpaceDTO dto, Long houseId) throws ClassNotFoundException {
        Space space = dtoConverter.SpaceDTOToEntity(dto);
        House house = houseRepository.findAllByHouseId(houseId);

        if (house == null) throw new ClassNotFoundException("House not found.");

        space.setHouse(house);
        house.getSpaceList().add(space);

        spaceRepository.save(space);
    }

    public void editSpace(SpaceDTO dto, Long id) throws Exception{
        Space space = spaceRepository.findAllBySpaceId(id);
        if(space != null){
            space = dtoConverter.SpaceEntityToEntity(space, dto);
            spaceRepository.save(space);
        } else {
            throw new ClassNotFoundException("Space not found");
        }
    }

    public void deleteSpaceById(Long id) throws Exception {
        Space space = spaceRepository.findAllBySpaceId(id);
        if(space != null){
            spaceRepository.delete(space);
        } else {
            throw new ClassNotFoundException("Space not found");
        }
    }

    public List<SpaceWithListDTO> getAllSpaces(){
        List<Space> spaceList = spaceRepository.findAll();
        List<SpaceWithListDTO> spaceDTOS = new ArrayList<>();
        for(Space s: spaceList){
            SpaceWithListDTO space = new SpaceWithListDTO();
            space.setName(s.getName());
            space.setDescription(s.getDescription());
            space.setDeviceList(new ArrayList<>());
            for (Device d: s.getDeviceList()) {
                space.getDeviceList().add(dtoConverter.DeviceEntityToDTO(d));
            }
            spaceDTOS.add(space);
        }
        return spaceDTOS;
    }

    public SpaceDTO getSpaceById(Long id) throws Exception {
        Space space = spaceRepository.findAllBySpaceId(id);
        if(space != null) {
            return dtoConverter.SpaceEntityToDTO(space);
        }else {
            throw new ClassNotFoundException("Space not found");
        }
    }
    public List<Device> getDevicesFromSpace(Long spaceId) throws Exception{
        Space space = spaceRepository.findAllBySpaceId(spaceId);
        if(space == null){
            throw new ClassNotFoundException("Space not found");
        }
        return space.getDeviceList();
    }
}
