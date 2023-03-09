package be.ucll.gip5.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class HouseWithListDTO {
    @Getter
    @Setter
    private Long houseId;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private List<SpaceDTO> spaceList;
}
