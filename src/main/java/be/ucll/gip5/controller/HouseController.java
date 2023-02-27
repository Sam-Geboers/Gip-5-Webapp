package be.ucll.gip5.controller;

import be.ucll.gip5.dto.HouseDTO;
import be.ucll.gip5.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @PostMapping
    public ResponseEntity addHouse(@RequestBody HouseDTO houseDTO){
        try{
            houseService.addHouse(houseDTO);
            return new ResponseEntity("House successfully created", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity editHouseById(@PathVariable Long id, @RequestBody HouseDTO houseDTO){
        try {
            houseService.editHouse(houseDTO, id);
            return new ResponseEntity("House was successfully edited", HttpStatus.OK);
        }
        catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public  ResponseEntity deleteHouseById(@PathVariable Long id){
        try {
            houseService.deleteHouseById(id);
            return new ResponseEntity("House was successfully deleted", HttpStatus.OK);
        }
        catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAllHouses(){
        try {
            List<HouseDTO> houseDTOList = houseService.getAllHouses();
            return new ResponseEntity<>(houseDTOList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}