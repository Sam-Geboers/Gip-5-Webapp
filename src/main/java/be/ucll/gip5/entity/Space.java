package be.ucll.gip5.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long spaceId;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @ManyToOne
    private House house;

    @Getter
    @Setter
    @OneToMany(mappedBy = "space")
    private List<Device> deviceList;
}
