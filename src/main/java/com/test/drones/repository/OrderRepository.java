package com.test.drones.repository;

import com.test.drones.model.entity.OrderEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends BaseRepository<OrderEntity> {

    OrderEntity findByDroneSerialNumber(String droneSN);

}
