package com.test.drones.service;

import com.test.drones.enums.DroneState;
import com.test.drones.model.dto.MedicationDTO;
import com.test.drones.model.dto.OrderDTO;
import com.test.drones.model.entity.DroneEntity;
import com.test.drones.model.entity.MedicationEntity;
import com.test.drones.model.entity.OrderEntity;
import com.test.drones.model.request.OrderRequest;
import com.test.drones.repository.DroneRepository;
import com.test.drones.repository.MedicationRepository;
import com.test.drones.repository.OrderRepository;
import com.test.drones.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final DroneRepository droneRepository;

    private final OrderRepository orderRepository;

    private final MedicationRepository medicationRepository;

    public OrderDTO createOrder(final String droneSN, final OrderRequest orderRequest) {

        final DroneEntity droneEntity = droneRepository.findBySerialNumber(droneSN);
        validateRequest(droneEntity, orderRequest);
        List<MedicationEntity> medicationEntityList = MedicationEntity.fromRequestList(orderRequest.getMedications());
        medicationRepository.saveAll(medicationEntityList);

        OrderEntity orderEntity = OrderEntity.builder()
                .drone(droneEntity)
                .medications(medicationEntityList)
                .build();

        orderRepository.save(orderEntity);
        return OrderDTO.fromEntity(orderEntity);
    }

    private void validateRequest(final DroneEntity droneEntity, final OrderRequest orderRequest) {
        if(!droneEntity.getState().equals(DroneState.IDLE)){
            throw new ValidationException("This drone is not ready");
        }
        if(droneEntity.getBatteryCapacity() < Constants.LOWER_BATTERY_CAPACITY){
            throw new ValidationException("This drone battery is lower than 25%");
        }
        int totalWeight = orderRequest.getMedications().stream()
                .map((request) -> request.getWeight())
                .reduce((weight1, weight2) -> weight1 + weight2)
                .get();
        if(droneEntity.getWeightLimit() < totalWeight){
            throw new ValidationException("This drone weight limit is lower than total weight");
        }
    }

    public List<MedicationDTO> getMedicationsForDrone(final String droneSN) {

        final OrderEntity orderEntity = orderRepository.findByDroneSerialNumber(droneSN);
        return MedicationDTO.fromEntityList(orderEntity.getMedications());
    }

}
