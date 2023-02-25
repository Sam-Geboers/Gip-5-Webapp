package be.ucll.gip5.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

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
