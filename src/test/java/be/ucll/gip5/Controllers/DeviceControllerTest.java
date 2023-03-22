package be.ucll.gip5.Controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceControllerTest {

    @Autowired
    private WebTestClient client;

    private static class DeviceBody{
        public DeviceBody(String name){
            this.name = name;
        }
        public String name;
    }

    @Test
    @DisplayName("Test should pass if Device is successfully created")
    public void addDeviceSuccessfully(){
        DeviceBody body = new DeviceBody("device");

        client.post()
                .uri("devices/add-device/1")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("Test should pass if Device is NOT created")
    public void addDeviceBadRequest(){
        DeviceBody body = new DeviceBody("device");

        client.post()
                .uri("devices/add-device/1")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @DisplayName("Test should pass if Device is successfully edited")
    public void editDeviceSuccessfully(){
        DeviceBody body = new DeviceBody("device");

        client.post()
                .uri("devices/edit-device/1")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if Device is NOT edited")
    public void editDeviceNotFound(){
        DeviceBody body = new DeviceBody("device");

        client.post()
                .uri("devices/edit-device/1")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if Device is successfully deleted")
    public void deleteDeviceSuccessfully(){
        DeviceBody body = new DeviceBody("device");

        client.post()
                .uri("devices/delete-device/1")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if Device is NOT deleted")
    public void deleteDeviceNotFound(){
        DeviceBody body = new DeviceBody("device");

        client.post()
                .uri("devices/delete-device/1")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if Device by id is successfully found")
    public void getDeviceSuccessfully(){

        client.post()
                .uri("devices/get-device/1")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if ALL Devices is NOT found")
    public void getDeviceNotSuccessfully(){

        client.post()
                .uri("devices/get-device/1")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if ALL Devices is NOT found")
    public void getAllDeviceSuccessfully(){

        client.post()
                .uri("devices/get-device")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is successfully added to Device")
    public void addDeviceInfoToDeviceSuccessfully(){

        client.post()
                .uri("devices/add-deviceInfo-to-device/1/1")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if DeviceInfo is successfully added to Device")
    public void addDeviceInfoToDeviceNotSuccessfully(){

        client.post()
                .uri("devices/add-deviceInfo-to-device/1/1")
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
