package be.ucll.gip5.controller;

import be.ucll.gip5.dto.SpaceDTO;
import be.ucll.gip5.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("space")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @PostMapping
    public ResponseEntity addSpace(@RequestBody SpaceDTO spaceDTO){
        try{
            spaceService.addSpace(spaceDTO);
            return new ResponseEntity("Space successfully created", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
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

    @DeleteMapping("{id}")
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

    @GetMapping()
    public ResponseEntity getAllSpaces(){
        try {
            List<SpaceDTO> spaceDTOList = spaceService.getAllSpaces();
            return new ResponseEntity<>(spaceDTOList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getSpaceById(@PathVariable Long id){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getPrincipal().toString();
            System.out.println(name);

            SpaceDTO dto = spaceService.getSpaceById(id);
            return new ResponseEntity<>(dto,HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
