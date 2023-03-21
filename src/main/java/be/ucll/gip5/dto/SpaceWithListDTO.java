package be.ucll.gip5.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SpaceWithListDTO {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private List<DeviceDTO> deviceList;
}
