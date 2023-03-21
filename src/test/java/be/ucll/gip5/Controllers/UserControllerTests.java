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
public class UserControllerTests {

    @Autowired
    private WebTestClient client;

    @Test
    @DisplayName("Test should pass if User is successfully created")
    public void addUserSuccessfully(){
        UserBody userBody = new UserBody(
                "user",
                "user@mail.com",
                "1234",
                "USER");

        client.post()
                .uri("users/add-user")
                .bodyValue(userBody)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("Test should pass if User not created")
    public void addUserBadRequest(){
        UserBody userBody = new UserBody(
                "",
                "user@mail.com",
                "1234",
                "USER");

        client.post()
                .uri("users/add-user")
                .bodyValue(userBody)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @DisplayName("Test should pass if User is successfully edited")
    public void editUserSuccessfully(){

    }

    @AfterEach
    public void cleanUpEach(){
        UserBody userBody = new UserBody(
                "",
                "",
                "",
                "");
    }


    private static class UserBody{

        public UserBody(String username, String email, String password, String roles){
            this.username = username;
            this.email = email;
            this.password = password;
            this.roles = roles;
        }
        public String username;
        public String email;
        public String password;
        public String roles;
    }
}
