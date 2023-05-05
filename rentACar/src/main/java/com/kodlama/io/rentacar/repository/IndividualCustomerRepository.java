package com.kodlama.io.rentacar.repository;

import com.kodlama.io.rentacar.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer,Integer> {
}
