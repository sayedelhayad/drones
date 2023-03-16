package com.test.drones.controller;

import com.test.drones.enums.DroneState;
import com.test.drones.model.dto.DroneDTO;
import com.test.drones.model.dto.MedicationDTO;
import com.test.drones.model.dto.OrderDTO;
import com.test.drones.model.request.DroneRequest;
import com.test.drones.model.request.OrderRequest;
import com.test.drones.service.DronesService;
import com.test.drones.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/drones")
public class DronesController {

    @Autowired
    private DronesService dronesService;
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneRequest droneRequest) {

        return new ResponseEntity(dronesService.registerDrone(droneRequest), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{droneSN}/load", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> loadDrone(@PathVariable String droneSN, @Valid @RequestBody OrderRequest orderRequest) {

        return new ResponseEntity(orderService.createOrder(droneSN, orderRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/{droneSN}/medications")
    public ResponseEntity<List<MedicationDTO>> getMedications(@PathVariable String droneSN) {

        return new ResponseEntity(orderService.getMedicationsForDrone(droneSN), HttpStatus.OK);
    }

    @GetMapping(value = "/state/{droneState}")
    public ResponseEntity<List<DroneDTO>> getDronesByState(@PathVariable DroneState droneState) {

        return new ResponseEntity(dronesService.getDronesByState(droneState), HttpStatus.OK);
    }

    @GetMapping(value = "/state/IDLE")
    public ResponseEntity<List<DroneDTO>> getAvailableDrones() {

        return new ResponseEntity(dronesService.getAvailableDrones(), HttpStatus.OK);
    }

    @GetMapping(value = "/{droneSN}/battery-level")
    public ResponseEntity<Integer> getDronesBatteryLevel(@PathVariable String droneSN) {
        return new ResponseEntity(dronesService.getDronesBatteryLevel(droneSN), HttpStatus.CREATED);
    }
}
