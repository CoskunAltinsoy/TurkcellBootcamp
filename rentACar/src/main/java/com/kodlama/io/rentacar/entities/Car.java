package com.kodlama.io.rentacar.entities;

import com.kodlama.io.rentacar.entities.enums.CarState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private CarState carState;
    @Column(name = "daily_price")
    private double dailyPrice;
    @ManyToOne()
    @JoinColumn(name = "model_id")
    private Model model;
    @OneToMany(mappedBy = "car")
    private List<Maintenance> maintenances;

}
