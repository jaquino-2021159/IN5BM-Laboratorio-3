package com.jorgeaquino.AutoRepuestosGT.repository;

import com.jorgeaquino.AutoRepuestosGT.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {
    Optional<Personal> findByEmailPersonal(String emailPersonal);
}