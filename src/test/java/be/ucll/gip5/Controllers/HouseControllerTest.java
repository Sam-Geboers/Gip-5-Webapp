package be.ucll.gip5.Controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HouseControllerTest {

    @Autowired
    private WebTestClient client;

    private static class HouseBody{

        public HouseBody (String name, String adress){
            this.name = name;
            this.adress = adress;
        }

        public String adress;
        public String name;
    }

    @AfterEach
    public void cleanUpEach(){
        HouseBody houseBody = new HouseBody(
                "",
                "");
    }

    @Test
    @DisplayName("Test should pass if House is successfully created")
    public void addHouseSuccessfully(){
        HouseBody houseBody = new HouseBody("House", "TestStreet 1");

        client.post()
                .uri("houses/add-house")
                .bodyValue(houseBody)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("Test should pass if house can not be created")
    public void addHouseBadRequest(){
        HouseBody houseBody = new HouseBody("", "TestStreet 1");

        client.post()
                .uri("houses/add-house")
                .bodyValue(houseBody)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @DisplayName("Test should pass if house can be edited successfully")
    public void editHouseSuccessfully(){
        String houseName = "house2";

        client.put()
                .uri("houses/edit-house/1")
                .bodyValue(houseName)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if house can not be edited")
    public void editHouseBadRequest(){
        String houseName = "house1";

        client.put()
                .uri("houses/edit/house/1")
                .bodyValue(houseName)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @DisplayName("Test should pass if house can be successfully deleted")
    public void deleteHouseSuccessfully(){
         client.delete()
                 .uri("houses/delete-house/1")
                 .exchange()
                 .expectStatus()
                 .isOk();
    }

    @Test
    @DisplayName("Test should pass if house can not be deleted")
    public void deleteHouseNotFound(){
        client.delete()
                .uri("houses/delete-house/1000000000000")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if all houses are found")
    public void getAllHousesSuccessfully(){
        client.get()
                .uri("houses/get-houses")
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    @DisplayName("Test should pass if not all houses are found")
    public void getAllHousesNotFound(){
        client.get()
                .uri("houses/get-houses")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if user is added to house")
    public void addUserToHouseSuccesfully(){
        client.post()
                .uri("houses/add-user-to-house/1/1")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if user can not be added to house")
    public void addUserToHouseNotFound(){
        client.post()
                .uri("houses/add-user-to-house/99/1")
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
