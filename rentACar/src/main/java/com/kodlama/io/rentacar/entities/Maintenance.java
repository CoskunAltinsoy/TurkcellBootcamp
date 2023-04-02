package com.kodlama.io.rentacar.entities;

import com.kodlama.io.rentacar.entities.enums.CarState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "maintenences")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate sendingDate;
    private LocalDate returnDate;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
