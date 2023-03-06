package be.ucll.gip5.service;

import be.ucll.gip5.dto.HouseDTO;
import be.ucll.gip5.entity.House;
import be.ucll.gip5.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private DTOConverter dtoConverter;

    public void addHouse(HouseDTO houseDTO){
        House house = dtoConverter.HouseDTOToEntity(houseDTO);
        houseRepository.save(house);
    }

    public void editHouse(HouseDTO dto, Long id) throws Exception{
        House house = houseRepository.findAllByHouseId(id);
        if (house != null){
            house = dtoConverter.HouseEntityToEntity(house, dto);
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

    public List<HouseDTO> getAllHouses(){
        List<House> houseList = houseRepository.findAll();
        List<HouseDTO> houseDTOS = new ArrayList<>();
        for(House h: houseList){
            houseDTOS.add(dtoConverter.HouseEntityToDTO(h));
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
}
