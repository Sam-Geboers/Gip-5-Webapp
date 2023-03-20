package be.ucll.gip5.service;

import be.ucll.gip5.dto.HouseDTO;
import be.ucll.gip5.dto.HouseWithListDTO;
import be.ucll.gip5.entity.House;
import be.ucll.gip5.entity.Space;
import be.ucll.gip5.entity.User;
import be.ucll.gip5.repository.HouseRepository;
import be.ucll.gip5.repository.SpaceRepository;
import be.ucll.gip5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
    private DTOConverter dtoConverter;
    @Autowired
    private UserRepository userRepository;

    public void addHouse(HouseDTO houseDTO) throws ClassNotFoundException {
        House house = dtoConverter.HouseDTOToEntity(houseDTO);
        if (house.getName().equals(houseRepository.findByName(house.getName()))){
            throw new ClassNotFoundException("Name already exists");
        }
        houseRepository.save(house);
    }

    public void editHouse(HouseDTO dto, Long id) throws Exception{
        House house = houseRepository.findAllByHouseId(id);
        if (house != null){
            house = dtoConverter.HouseEntityToEntity(house, dto);
            if (house.getName().equals(houseRepository.findByName(house.getName()))){
                throw new ClassNotFoundException("Name already exists");
            }
            houseRepository.save(house);
        }else {
            throw new ClassNotFoundException("House not Found");
        }
    }

    public void deleteHouseById(Long id) throws Exception{
        House house = houseRepository.findAllByHouseId(id);
        if (house != null) {
            houseRepository.delete(house);
        } else {
            throw new ClassNotFoundException("House not found");
        }
    }

    public List<HouseWithListDTO> getAllHouses(){
        List<House> houseList = houseRepository.findAll();
        List<HouseWithListDTO> houseDTOS = new ArrayList<>();
        for(House h: houseList){
            HouseWithListDTO house = new HouseWithListDTO();
            house.setName(h.getName());
            house.setAddress(h.getAddress());
            house.setSpaceList(new ArrayList<>());
            for (Space s:h.getSpaceList()) {
                house.getSpaceList().add(dtoConverter.SpaceEntityToDTO(s));
            }
            houseDTOS.add(house);
        }
        return houseDTOS;
    }

    public HouseDTO getHouseById(Long id) throws Exception {
        House house = houseRepository.findAllByHouseId(id);
        if(house != null){
            return dtoConverter.HouseEntityToDTO(house);
        }else{
            throw new ClassNotFoundException("House not found");
        }

    }

    public List<Space> getSpacesFromHouse(Long houseId) throws Exception{
        House house = houseRepository.findAllByHouseId(houseId);

        if (house == null) throw new ClassNotFoundException("House not found.");

        return house.getSpaceList();
    }

    public void addUserToHouse(Long houseId, Long userId) throws Exception{
        House house = houseRepository.findAllByHouseId(houseId);
        User user = userRepository.findAllByUserId(userId);

        if (house == null) {
            throw new ClassNotFoundException("House not found");
        }
        if (user == null) {
            throw new ClassNotFoundException("User not found");
        }
        user.getHouseList().add(house);
        house.getUserList().add(user);
        houseRepository.save(house);
    }

}
