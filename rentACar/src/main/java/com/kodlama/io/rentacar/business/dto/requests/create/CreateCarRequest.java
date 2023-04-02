package com.kodlama.io.rentacar.business.dto.requests.create;

import com.kodlama.io.rentacar.entities.Model;
import com.kodlama.io.rentacar.entities.enums.CarState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    private int modelId;
    private int modelYear;
    private String plate;
    private double dailyPrice;
}
