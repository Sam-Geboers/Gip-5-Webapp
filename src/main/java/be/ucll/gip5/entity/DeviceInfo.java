package be.ucll.gip5.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DeviceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deviceInfoId;
    private boolean deviceStatus;
    private String consumption;
    private String typeOfDevice;
    //miss veranderen naar json list
    private String deviceInformation;

    @OneToOne(mappedBy = "deviceInfo")
    private Device device;
}
