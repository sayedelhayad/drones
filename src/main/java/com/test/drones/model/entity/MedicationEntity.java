package com.test.drones.model.entity;

import com.test.drones.model.request.MedicationRequest;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name = "medication")
@Table(name = "medication")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int weight;

    private String code;

    private String image;

    public static MedicationEntity fromRequest(MedicationRequest request){
        return MedicationEntity.builder()
                .name(request.getName())
                .code(request.getCode())
                .weight(request.getWeight())
                .image(request.getImage())
                .build();
    }

    public static List<MedicationEntity> fromRequestList(List<MedicationRequest> requestList){
        return requestList.stream().map((request) -> fromRequest(request)).collect(Collectors.toList());
    }

}
