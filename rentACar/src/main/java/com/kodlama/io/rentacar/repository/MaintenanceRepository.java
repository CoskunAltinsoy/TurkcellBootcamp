package com.kodlama.io.rentacar.repository;

import com.kodlama.io.rentacar.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance,Integer> {
    boolean existsByCarIdAndIsCompletedIsFalse(int id);
    Maintenance findByCarIdAndIsCompletedIsFalse(int id);
}
