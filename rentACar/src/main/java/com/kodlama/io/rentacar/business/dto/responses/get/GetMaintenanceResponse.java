package com.kodlama.io.rentacar.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMaintenanceResponse {
    private int id;
    private LocalDate sendingDate;
    private LocalDate returnDate;
    private int carId;
}
