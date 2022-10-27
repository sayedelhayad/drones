package com.test.drones.model.dto;

import com.test.drones.model.entity.MedicationEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicationDTO {
    private String name;

    private int weight;

    private String code;

    private String image;

    public static MedicationDTO fromEntity(final MedicationEntity entity) {
        return MedicationDTO.builder()
                .name(entity.getName())
                .code(entity.getCode())
                .image(entity.getImage())
                .weight(entity.getWeight())
                .build();
    }

    public static List<MedicationDTO> fromEntityList(final List<MedicationEntity> entityList) {
        return entityList.stream().map((entity) -> fromEntity(entity)).collect(Collectors.toList());
    }
}
