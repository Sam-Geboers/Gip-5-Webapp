package be.ucll.gip5.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class DeviceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long deviceInfoId;
    @Getter
    @Setter
    private boolean deviceStatus;
    @Getter
    @Setter
    private String consumption;
    @Getter
    @Setter
    private String typeOfDevice;
    @Getter
    @Setter
    private String deviceInformation;

    @Getter
    @Setter
    @OneToOne(mappedBy = "deviceInfo")
    private Device device;
}
