package be.ucll.gip5.service;

import be.ucll.gip5.dto.PersonDTO;
import be.ucll.gip5.dto.SpaceDTO;
import be.ucll.gip5.entity.Person;
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
    private DTOConverter dtoConverter;

    public void addSpace(SpaceDTO dto){
        Space space = dtoConverter.SpaceDTOToEntity(dto);
        spaceRepository.save(space);
    }

    public void editSpace(SpaceDTO dto, Long id) throws Exception{
        Space space = spaceRepository.findAllById(id);
        if(space != null){
            space = dtoConverter.SpaceEntityToEntity(space, dto);
            spaceRepository.save(space);
        } else {
            throw new ClassNotFoundException("Space not found");
        }
    }

    public void deleteSpaceById(Long id) throws Exception {
        Space space = spaceRepository.findAllById(id);
        if(space != null){
            spaceRepository.delete(space);
        } else {
            throw new ClassNotFoundException("Space not found");
        }
    }

    public List<SpaceDTO> getAllSpaces(){
        List<Space> spaceList = spaceRepository.findAll();
        List<SpaceDTO> spaceDTOS = new ArrayList<>();
        for(Space s: spaceList){
            spaceDTOS.add(dtoConverter.SpaceEntityToDTO(s));
        }
        return spaceDTOS;
    }
}
