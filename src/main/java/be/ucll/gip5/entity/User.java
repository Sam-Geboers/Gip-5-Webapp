package be.ucll.gip5.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String roles;

    @ManyToMany(mappedBy = "userList")
    private List<House> houseList;
}
