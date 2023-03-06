package be.ucll.gip5.controller;

import be.ucll.gip5.dto.DeviceDTO;
import be.ucll.gip5.dto.HouseDTO;
import be.ucll.gip5.dto.UserDTO;
import be.ucll.gip5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody UserDTO userDTO){
        try{
            userService.addUser(userDTO);
            return new ResponseEntity("User successfully created", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity editUserById(@PathVariable Long id, @RequestBody UserDTO userDTO){
        try {
            userService.editUser(userDTO, id);
            return new ResponseEntity("User was successfully edited", HttpStatus.OK);
        }
        catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public  ResponseEntity deleteUserById(@PathVariable Long id){
        try {
            userService.deleteUserById(id);
            return new ResponseEntity("User was successfully deleted", HttpStatus.OK);
        }
        catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("{id}")
//    public ResponseEntity getUserById(@PathVariable Long id){
//        try {
//            UserDTO userDTO = userService.getUserById(id);
//            return new ResponseEntity<>(userDTO, HttpStatus.OK);
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity(e, HttpStatus.NOT_FOUND);
//        }
//    }

//    @GetMapping
//    public ResponseEntity getAllUsers(){
//        try {
//            List<UserDTO> userDTOSList = userService.getAllUsers();
//            return new ResponseEntity<>(userDTOSList, HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
//        }
//    }
}
