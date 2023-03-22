package be.ucll.gip5.Controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceInfoControllerTest {

    @Autowired
    private WebTestClient client;

    private static class DeviceInfoBody{
        public DeviceInfoBody(boolean deviceStatus,String consumption, String typeOfDevice, String deviceInformation){
            this.deviceStatus = deviceStatus;
            this.consumption = consumption;
            this.typeOfDevice = typeOfDevice;
            this.deviceInformation = deviceInformation;
        }
        public boolean deviceStatus;
        public String consumption;
        public String typeOfDevice;
        public String deviceInformation;
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is succesfully created")
    public void addDeviceInfoCreated(){
        DeviceInfoBody body =new DeviceInfoBody(false, "10kwh","blender","1234");

        client.post()
                .uri("deviceInfo/add-device-info")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is NOT created")
    public void addDeviceInfoFailed(){
        DeviceInfoBody body =new DeviceInfoBody(false, "10kwh","blender","1234");

        client.post()
                .uri("deviceInfo/add-device-info")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is succesfully edited")
    public void editDeviceInfoOk(){
        DeviceInfoBody body =new DeviceInfoBody(false, "10kwh","blender","1234");

        client.post()
                .uri("deviceInfo/edit-device-info/1")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is NOT edited")
    public void editDeviceInfoNotFound(){
        DeviceInfoBody body =new DeviceInfoBody(false, "10kwh","blender","1234");

        client.post()
                .uri("deviceInfo/edit-device-info/1")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is succesfully deleted")
    public void deleteDeviceInfoDeleted(){
        DeviceInfoBody body =new DeviceInfoBody(false, "10kwh","blender","1234");

        client.post()
                .uri("deviceInfo/delete-device-info/1")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is NOT deleted")
    public void deleteDeviceInfoNotFound(){
        DeviceInfoBody body =new DeviceInfoBody(false, "10kwh","blender","1234");

        client.post()
                .uri("deviceInfo/delete-device-info/1")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is succesfully found")
    public void getDeviceInfoFound(){

        client.post()
                .uri("deviceInfo/get-device-info/1")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is NOT found")
    public void getDeviceInfoNotFound(){

        client.post()
                .uri("deviceInfo/get-device-info/1")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is succesfully found")
    public void getAllDeviceInfoFound(){

        client.post()
                .uri("deviceInfo/get-device-info")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is NOTfound")
    public void getAllDeviceInfoNotFound(){

        client.post()
                .uri("deviceInfo/get-device-info")
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
