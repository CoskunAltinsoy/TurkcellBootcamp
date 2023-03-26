package com.kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "model_year")
    private int modelYear;
    @Column(name = "plate")
    private String plate;
    @Column(name = "state")
    private int state;
    @Column(name = "daily_price")
    private double dailyPrice;
}
