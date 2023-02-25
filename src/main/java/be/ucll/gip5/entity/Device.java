package be.ucll.gip5.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @OneToOne(mappedBy = "device")
    private DeviceInfo deviceInfo;
}