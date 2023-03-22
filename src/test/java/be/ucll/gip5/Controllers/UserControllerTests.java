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

    @AfterEach
    public void cleanUpEach(){
        UserBody userBody = new UserBody(
                "",
                "",
                "",
                "");
    }

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
        String username = "user2";

        client.put()
                .uri("users/edit-user/1")
                .bodyValue(username)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("Test should pass if User could not be edited")
    public void editUserBadRequest(){
        String username = "";

        client.put()
                .uri("users/edit-user/1")
                .bodyValue(username)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @DisplayName("Test should pass if User is successfully deleted")
    public void deleteUserSuccessfully(){
        client.delete()
                .uri("users/delete-user/1")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if User was not found to delete")
    public void deleteUserNotFound(){
        client.delete()
                .uri("users/delete-user/100052/845121")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if User was successfully found by id")
    public void getUserByIdSuccessfully(){
        client.get()
                .uri("users/1")
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    @DisplayName("Test should pass if User was not found by id")
    public void getUserByIdNotFound(){
        client.get()
                .uri("users/10000000000000000")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if all users are found")
    public void getAllUsersSuccessfully(){
        client.get()
                .uri("users")
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    @DisplayName("Test should pass if not all users are found")
    public void getAllUsersNotFound(){
        client.get()
                .uri("users")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if user is found by email")
    public void getUserByEmailSuccessfully(){
        client.get()
                .uri("users/get-user-by-email/user@mail.com")
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    @DisplayName("Test should pass if user is not found by email")
    public void getUserByEmailNotFound(){
        client.get()
                .uri("users/get-user-by-email/uuser@mail.com")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("Test should pass if user can successfully login")
    public void loginSuccessfully(){
        client.get()
                .uri("users/login/user@mail.com/1234")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Test should pass if user can not login")
    public void loginFailed(){
        client.get()
                .uri("users/login/user@mail.com/12345")
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

}
