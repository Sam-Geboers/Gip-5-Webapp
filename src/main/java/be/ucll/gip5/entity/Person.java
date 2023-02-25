package be.ucll.gip5.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long personId;
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

    @Getter
    @Setter
    @ManyToMany(mappedBy = "personList")
    private List<House> houseList;
}
