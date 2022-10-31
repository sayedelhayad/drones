package com.test.drones.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationRequest {
    @NotBlank
    private String name;

    private int weight;

    private String code;

    private String image;
}
