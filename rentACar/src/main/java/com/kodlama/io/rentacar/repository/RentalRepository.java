package com.kodlama.io.rentacar.repository;

import com.kodlama.io.rentacar.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    boolean existsByCarIdAndIsCompletedIsFalse(int carId);
    Rental findByCarIdAndIsCompletedIsFalse(int carId);
}
