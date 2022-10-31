package com.test.drones.model.request;

import com.test.drones.enums.DroneModel;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DroneRequest {
    @NotBlank
    @Size(max = 100)
    private String serialNumber;

    @NonNull
    private DroneModel model;

    @Max(500)
    private int weightLimit;
}
