package be.ucll.gip5.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;
    private String address;
    private String name;

    @ManyToMany
    private List<User> userList;

    @OneToMany(mappedBy = "house")
    private List<Space> spaceList;
}
