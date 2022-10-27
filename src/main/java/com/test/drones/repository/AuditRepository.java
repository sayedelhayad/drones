package com.test.drones.repository;

import com.test.drones.model.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long>
        , CrudRepository<AuditEntity, Long>
        , JpaSpecificationExecutor<AuditEntity> {
}
