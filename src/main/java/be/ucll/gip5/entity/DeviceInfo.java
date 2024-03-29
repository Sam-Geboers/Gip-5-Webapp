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
    private Long deviceInfoId;
    private boolean deviceStatus;
    private String consumption;
    private String typeOfDevice;
    private String deviceInformation;

    @OneToOne(mappedBy = "deviceInfo")
    private Device device;
}
