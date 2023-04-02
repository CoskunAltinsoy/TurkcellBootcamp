package com.kodlama.io.rentacar.business.dto.responses.get;

import com.kodlama.io.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarsResponse {
    private int id;
    private int modelId;
    private int modelYear;
    private String plate;
    private CarState carState;
    private double dailyPrice;
}
