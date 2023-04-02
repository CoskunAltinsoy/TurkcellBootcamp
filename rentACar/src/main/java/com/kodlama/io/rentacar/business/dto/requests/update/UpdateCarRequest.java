package com.kodlama.io.rentacar.business.dto.requests.update;

import com.kodlama.io.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
    private int id;
    private int modelId;
    private int modelYear;
    private String plate;
    private CarState carState;
    private double dailyPrice;
}
