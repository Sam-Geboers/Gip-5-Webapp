package be.ucll.gip5.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long houseId;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToMany
    private List<Person> personList;

    @Getter
    @Setter
    @OneToMany(mappedBy = "house")
    private List<Space> spaceList;
}
