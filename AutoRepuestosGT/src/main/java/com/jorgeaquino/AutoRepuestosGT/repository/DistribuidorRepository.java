package com.jorgeaquino.AutoRepuestosGT.repository;

import com.jorgeaquino.AutoRepuestosGT.model.Distribuidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistribuidorRepository extends JpaRepository<Distribuidor, Integer> {
    Optional<Distribuidor> findByEmailDistribuidor(String email_distribuidor);
    Optional<Distribuidor> findByNombreDistribuidor(String nombre_distribuidor);
}