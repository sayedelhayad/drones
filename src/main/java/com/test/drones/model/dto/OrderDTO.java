package com.test.drones.model.dto;

import com.test.drones.model.entity.OrderEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO implements Serializable {

    private DroneDTO drone;

    private List<MedicationDTO> medications;


    public static OrderDTO fromEntity(final OrderEntity entity) {
        return OrderDTO.builder()
                .drone(DroneDTO.fromEntity(entity.getDrone()))
                .medications(MedicationDTO.fromEntityList(entity.getMedications()))
                .build();
    }

    public static List<OrderDTO> fromEntityList(final List<OrderEntity> entityList) {
        return entityList.stream().map((entity) -> fromEntity(entity)).collect(Collectors.toList());
    }
}
