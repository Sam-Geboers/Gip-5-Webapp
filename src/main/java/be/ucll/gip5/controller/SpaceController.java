package be.ucll.gip5.controller;

import be.ucll.gip5.dto.SpaceDTO;
import be.ucll.gip5.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spaces")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @PostMapping("add-space")
    public ResponseEntity addSpace(@RequestBody SpaceDTO spaceDTO){
        try{
            spaceService.addSpace(spaceDTO);
            return new ResponseEntity("Space successfully created", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit-space/{id}")
    public ResponseEntity editSpaceById(@PathVariable Long id, @RequestBody SpaceDTO spaceDTO){
        try {
            spaceService.editSpace(spaceDTO, id);
            return new ResponseEntity("Space was successfully edited", HttpStatus.OK);
        }
        catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete-space/{id}")
    public  ResponseEntity deleteSpaceById(@PathVariable Long id){
        try {
            spaceService.deleteSpaceById(id);
            return new ResponseEntity("Space was successfully deleted", HttpStatus.OK);
        }
        catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-spaces")
    public ResponseEntity getAllSpaces(){
        try {
            List<SpaceDTO> spaceDTOList = spaceService.getAllSpaces();
            return new ResponseEntity<>(spaceDTOList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-space/{id}")
    public ResponseEntity getSpaceById(@PathVariable Long id){
        try{
            SpaceDTO dto = spaceService.getSpaceById(id);
            return new ResponseEntity<>(dto,HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
