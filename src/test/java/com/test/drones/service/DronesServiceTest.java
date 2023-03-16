package com.test.drones.service;

import com.test.drones.DroneApplication;
import com.test.drones.enums.DroneModel;
import com.test.drones.enums.DroneState;
import com.test.drones.model.dto.DroneDTO;
import com.test.drones.model.entity.DroneEntity;
import com.test.drones.model.request.DroneRequest;
import com.test.drones.repository.DroneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = {DroneApplication.class})
@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackageClasses = DroneRepository.class)
@EntityScan(basePackageClasses = DroneEntity.class)

public class DronesServiceTest {

    @Autowired
    private DronesService dronesService;

    @Test
    @DirtiesContext
    public void registerDrone() {
        DroneRequest droneRequest = DroneRequest.builder()
                .model(DroneModel.Cruiserweight)
                .serialNumber("serialNumber")
                .weightLimit(500)
                .build();

        DroneDTO droneDTO = dronesService.registerDrone(droneRequest);
        assertTrue(droneDTO.getState() == DroneState.IDLE);
    }

    @Test
    @DirtiesContext
    public void getDronesByState() {
        registerDrone();
        DroneState state = DroneState.IDLE;

        List<DroneDTO> droneDTOList = dronesService.getDronesByState(state);
        assertTrue(droneDTOList.size() > 0);
        assertEquals(droneDTOList.get(0).getSerialNumber(), "serialNumber");
    }

    @Test
    @DirtiesContext
    public void getDronesBatteryLevel() {
        registerDrone();
        String sn = "serialNumber";

        Integer level = dronesService.getDronesBatteryLevel(sn);
        assertTrue(level == 100);
    }
}
