package com.test.drones.model.entity;

import com.test.drones.enums.DroneModel;
import com.test.drones.enums.DroneState;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "drone")
@Table(name = "drone")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DroneModel model;

    private int weightLimit;

    private int batteryCapacity = 100;

    @Enumerated(EnumType.STRING)
    private DroneState state = DroneState.IDLE;

    @OneToMany
    private List<OrderEntity> orders;
}
