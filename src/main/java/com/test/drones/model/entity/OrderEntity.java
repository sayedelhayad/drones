package com.test.drones.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "orders")
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity {

    @ManyToOne
    private DroneEntity drone;

    @OneToMany(fetch = FetchType.EAGER)
    private List<MedicationEntity> medications;
}
