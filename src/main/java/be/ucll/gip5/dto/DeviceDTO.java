package be.ucll.gip5.dto;

import lombok.Getter;
import lombok.Setter;

public class DeviceDTO {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private DeviceInfoDTO deviceInfo;
}
