package com.kodlama.io.rentacar.repository;

import com.kodlama.io.rentacar.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
