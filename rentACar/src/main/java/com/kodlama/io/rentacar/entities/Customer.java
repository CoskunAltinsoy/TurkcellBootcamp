package com.kodlama.io.rentacar.entities;

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
@Table(name = "customers")
@PrimaryKeyJoinColumn(name="customer_id")
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    List<Rental> rentals;
}
