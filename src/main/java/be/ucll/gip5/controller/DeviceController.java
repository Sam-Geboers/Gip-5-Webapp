package be.ucll.gip5.controller;

import be.ucll.gip5.dto.DeviceDTO;
import be.ucll.gip5.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/add-device/{id}")
    public ResponseEntity addDevice(@RequestBody DeviceDTO deviceDTO, @PathVariable Long id){
        try{
            deviceService.addDevice(deviceDTO, id);
            return new ResponseEntity<>("Device successfully created", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit-device/{id}")
    public ResponseEntity editDeviceById(@PathVariable Long id, @RequestBody DeviceDTO deviceDTO){
        try {
            deviceService.editDevice(id, deviceDTO);
            return new ResponseEntity<>("Device was successfully edited", HttpStatus.OK);
        }
        catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-device/{id}")
    public  ResponseEntity deleteDeviceById(@PathVariable Long id){
        try {
            deviceService.deleteDevice(id);
            return new ResponseEntity<>("Device was successfully deleted", HttpStatus.OK);
        }
        catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-device/{id}")
    public ResponseEntity getDeviceById(@PathVariable Long id){
        try {
            DeviceDTO deviceDTO = deviceService.getDevice(id);
            return new ResponseEntity<>(deviceDTO, HttpStatus.OK);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-devices")
    public ResponseEntity getAllDevice(){
        try {
            List<DeviceDTO> deviceDTOList = deviceService.getALlDevices();
            return new ResponseEntity<>(deviceDTOList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-deviceInfo-to-device/{deviceId}/{deviceInfoId}")
    public ResponseEntity addDeviceInfoToDevice(@PathVariable Long deviceId, @PathVariable Long deviceInfoId){
        try{
            deviceService.addDeviceInfoToDevice(deviceId, deviceInfoId);
            return new ResponseEntity<>("DeviceInfo was successfully added to device", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }


}
