package be.ucll.gip5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Device {
    @Id
    private Long deviceId;
}
