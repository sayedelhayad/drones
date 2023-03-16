package com.test.drones.service;

import com.test.drones.DroneApplication;
import com.test.drones.enums.DroneModel;
import com.test.drones.model.dto.DroneDTO;
import com.test.drones.model.dto.MedicationDTO;
import com.test.drones.model.dto.OrderDTO;
import com.test.drones.model.entity.OrderEntity;
import com.test.drones.model.request.DroneRequest;
import com.test.drones.model.request.MedicationRequest;
import com.test.drones.model.request.OrderRequest;
import com.test.drones.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = {DroneApplication.class})
@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackageClasses = {OrderRepository.class, OrderRepository.class})
@EntityScan(basePackageClasses = {OrderEntity.class, OrderEntity.class})

public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DronesService dronesService;

    @Test
    @DirtiesContext
    public void createOrder() {
        DroneDTO droneDTO = registerDrone();
        MedicationRequest medicationRequest = MedicationRequest.builder()
                .name("name")
                .code("code")
                .image("image")
                .weight(100)
                .build();
        List<MedicationRequest> medications = Collections.singletonList(medicationRequest);

        final OrderRequest orderRequest = OrderRequest.builder()
                .medications(medications)
                .build();

        OrderDTO orderDTO = orderService.createOrder(droneDTO.getSerialNumber(), orderRequest);
        assertTrue(orderDTO.getDrone().getSerialNumber() == droneDTO.getSerialNumber());
        assertTrue(orderDTO.getMedications().size() == 1);
    }

    @Test
    @DirtiesContext
    public void getMedicationsForDrone() {
        createOrder();

        final List<MedicationDTO> medicationDTOList = orderService.getMedicationsForDrone("serialNumber");
        assertTrue(medicationDTOList.size() == 1);
    }

    private DroneDTO registerDrone() {
        DroneRequest droneRequest = DroneRequest.builder()
                .model(DroneModel.Cruiserweight)
                .serialNumber("serialNumber")
                .weightLimit(500)
                .build();

        DroneDTO droneDTO = dronesService.registerDrone(droneRequest);
        return droneDTO;
    }
}
