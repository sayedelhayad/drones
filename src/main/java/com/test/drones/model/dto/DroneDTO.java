package com.test.drones.model.dto;

import com.test.drones.enums.DroneModel;
import com.test.drones.enums.DroneState;
import com.test.drones.model.entity.DroneEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DroneDTO {
    private String serialNumber;
    private DroneModel model;
    private int weightLimit;
    private int batteryCapacity;
    private DroneState state;

    public static DroneDTO fromEntity(final DroneEntity entity) {
        return DroneDTO.builder()
                .serialNumber(entity.getSerialNumber())
                .model(entity.getModel())
                .batteryCapacity(entity.getBatteryCapacity())
                .state(entity.getState())
                .weightLimit(entity.getWeightLimit())
                .build();
    }

    public static List<DroneDTO> fromEntityList(final List<DroneEntity> entityList) {
        return entityList.stream().map((entity) -> fromEntity(entity)).collect(Collectors.toList());
    }

}
