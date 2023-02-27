package be.ucll.gip5.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long deviceId;
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToOne
    private Space space;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deviceInfo_id")
    private DeviceInfo deviceInfo;
}