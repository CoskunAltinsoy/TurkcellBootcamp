package com.kodlama.io.rentacar.business.dto.requests.create;

import com.kodlama.io.rentacar.common.Regex;
import com.kodlama.io.rentacar.common.constants.Messages;
import com.kodlama.io.rentacar.common.utils.annotations.NotFutureYear;
import com.kodlama.io.rentacar.entities.Model;
import com.kodlama.io.rentacar.entities.enums.CarState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    @Min(0)
    private int modelId;
    @Min(1996)
    @NotFutureYear
    private int modelYear;
    @Pattern(regexp = Regex.Plate, message = Messages.Car.PlateNotValid)
    private String plate;
    @Min(1)
    @Max(100000)
    private double dailyPrice;
}
