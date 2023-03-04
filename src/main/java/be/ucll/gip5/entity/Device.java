package be.ucll.gip5.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;
    private String name;

    @ManyToOne
    private Space space;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deviceInfo_id")
    private DeviceInfo deviceInfo;
}