package com.test.drones.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationRequest {
    @NotBlank
    private String name;

    private int weight;

    private String code;

    private String image;
}
