package be.ucll.gip5.controller;

import be.ucll.gip5.dto.DeviceInfoDTO;
import be.ucll.gip5.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("devicesInfo")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;

    @PostMapping("/add-device-info")
    public ResponseEntity addDeviceInfo(@RequestBody DeviceInfoDTO dto){
        try{
            deviceInfoService.addDeviceInfo(dto);
            return new ResponseEntity<>("DeviceInfo successfully created", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit-device-info/{id}")
    public ResponseEntity editDeviceInfoById(@PathVariable Long id, @RequestBody DeviceInfoDTO deviceInfoDTO){
        try {
            deviceInfoService.editDeviceInfo(deviceInfoDTO, id);
            return new ResponseEntity<>("DeviceInfo was successfully edited", HttpStatus.OK);
        }
        catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-device-info/{id}")
    public  ResponseEntity deleteDeviceInfoById(@PathVariable Long id){
        try {
            deviceInfoService.deleteDeviceInfo(id);
            return new ResponseEntity<>("DeviceInfo was successfully deleted", HttpStatus.OK);
        }
        catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-device-info/{id}")
    public ResponseEntity getDeviceInfoById(@PathVariable Long id){
        try {
            DeviceInfoDTO deviceInfoDTO = deviceInfoService.getDeviceInfoById(id);
            return new ResponseEntity<>(deviceInfoDTO, HttpStatus.OK);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get-devices-info")
    public ResponseEntity getAllDeviceInfo(){
        try {
            List<DeviceInfoDTO> deviceInfoDTOS = deviceInfoService.getAllDeviceInfo();
            return new ResponseEntity<>(deviceInfoDTOS, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
