package be.ucll.gip5.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spaceId;
    private String name;
    private String description;

    @ManyToOne
    private House house;

    @OneToMany(mappedBy = "space")
    private List<Device> deviceList;
}
