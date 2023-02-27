package be.ucll.gip5.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long roleId;
    @Getter
    @Setter
    private String roleName;

    @Getter
    @Setter
    @OneToMany(mappedBy = "role")
    private List<User> userList;

}
