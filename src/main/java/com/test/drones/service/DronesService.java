package com.test.drones.service;

import com.test.drones.enums.DroneState;
import com.test.drones.model.dto.DroneDTO;
import com.test.drones.model.entity.AuditEntity;
import com.test.drones.model.entity.DroneEntity;
import com.test.drones.model.request.DroneRequest;
import com.test.drones.repository.AuditRepository;
import com.test.drones.repository.DroneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DronesService {

    private final DroneRepository droneRepository;
    private final AuditRepository auditRepository;

    public DroneDTO registerDrone(final DroneRequest droneRequest) {
        final DroneEntity droneEntity = DroneEntity.builder()
                .serialNumber(droneRequest.getSerialNumber())
                .model(droneRequest.getModel())
                .weightLimit(droneRequest.getWeightLimit())
                .state(DroneState.IDLE)
                .batteryCapacity(100)
                .build();

        droneRepository.save(droneEntity);

        return DroneDTO.fromEntity(droneEntity);
    }

    public List<DroneDTO> getDronesByState(final DroneState droneState) {
        final List<DroneEntity> doneEntityList = droneRepository.findByState(droneState);
        if(CollectionUtils.isEmpty(doneEntityList)){
            return null;
        }
        return DroneDTO.fromEntityList(doneEntityList);
    }

    public List<DroneDTO> getAvailableDrones() {
        return getDronesByState(DroneState.IDLE);
    }

    public Integer getDronesBatteryLevel(final String droneSN) {
        final DroneEntity doneEntity = droneRepository.findBySerialNumber(droneSN);
        return doneEntity.getBatteryCapacity();
    }

    public void checkBatteries() {
        final List<DroneEntity> doneEntityList = droneRepository.findByBatteryCapacityLessThan(25);
        final List<AuditEntity> auditEntityList = doneEntityList.stream().map((drone) -> AuditEntity.builder()
                .droneId(drone.getId())
                .batteryLevel(drone.getBatteryCapacity())
                .build()).collect(Collectors.toList());

        auditRepository.saveAll(auditEntityList);
    }

}
