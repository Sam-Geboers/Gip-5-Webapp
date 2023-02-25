package be.ucll.gip5.dto;

import lombok.Getter;
import lombok.Setter;

public class PersonDTO {
    @Getter
    @Setter
    private String role;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
}
