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
public class SpaceControllerTest {

    @Autowired
    private WebTestClient client;

    private static class SpaceBody{

        public SpaceBody (String name, String description){
            this.name = name;
            this.description = description;
        }
        public String description;
        public String name;
    }

    @AfterEach
    public void cleanUpEach(){
        SpaceBody spaceBody = new SpaceBody(
                "",
                ""
        );
    }

    @Test
    @DisplayName("Test should pass if Space is successfully created")
    public void addSpaceSuccessfully(){
        SpaceBody spaceBody = new SpaceBody(
                "space1",
                "description1");

        client.post()
                .uri("spaces/add-space")
                .bodyValue(spaceBody)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("Test should pass if space can not be created")
    public void addSpaceBadRequest(){
        SpaceBody spaceBody = new SpaceBody("", "TestSpace 1");

        client.post()
                .uri("spaces/add-space")
                .bodyValue(spaceBody)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @DisplayName("Test should pass if space can be edited successfully")
    public void editSpaceSuccessfully(){
        String spaceName = "space2";

        client.put()
                .uri("spaces/edit-space/1")
                .bodyValue(spaceName)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if space can not be edited")
    public void editSpaceBadRequest(){
        String spaceName = "space1";

        client.put()
                .uri("spaces/edit-space/1")
                .bodyValue(spaceName)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @DisplayName("Test should pass if space can be successfully deleted")
    public void deleteSpaceSuccessfully(){
        client.delete()
                .uri("spaces/delete-space/1")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if space can not be deleted")
    public void deleteSpaceNotFound(){
        client.delete()
                .uri("spaces/delete-space/1000000000000")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if all spaces are found")
    public void getAllSpacesSuccessfully(){
        client.get()
                .uri("spaces/get-spaces")
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    @DisplayName("Test should pass if not all spaces are found")
    public void getAllSpacesNotFound(){
        client.get()
                .uri("spaces/get-spaces")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if space is found")
    public void getSpaceByIdSuccessfully(){
        client.get()
                .uri("spaces/get-space/{id}")
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    @DisplayName("Test should pass if space is not found")
    public void getSpaceByIdNotFound(){
        client.get()
                .uri("spaces/get-space/{id}")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if devices from space is found")
    public void getDevicesFromSpaceSuccessfully(){
        client.get()
                .uri("spaces/get-devices-from-space/{spaceId}")
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    @DisplayName("Test should pass if devices from space are not found")
    public void getDevicesFromSpaceNotFound(){
        client.get()
                .uri("spaces/get-devices-from-space/{spaceId}")
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
