package com.test.drones.repository;

import com.test.drones.enums.DroneState;
import com.test.drones.model.entity.DroneEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DroneRepository extends BaseRepository<DroneEntity> {

    DroneEntity findBySerialNumber(String droneSN);

    List<DroneEntity> findByState(DroneState droneState);

    List<DroneEntity> findByBatteryCapacityLessThan(int level);

}
