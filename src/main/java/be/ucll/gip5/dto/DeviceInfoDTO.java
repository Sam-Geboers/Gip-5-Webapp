package be.ucll.gip5.dto;

import lombok.Getter;
import lombok.Setter;

public class DeviceInfoDTO {
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
}
