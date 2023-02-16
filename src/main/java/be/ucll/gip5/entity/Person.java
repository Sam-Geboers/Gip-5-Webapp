package be.ucll.gip5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    private Long personId;
}
