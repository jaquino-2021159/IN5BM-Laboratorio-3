package com.jorgeaquino.AutoRepuestosGT.repository;

import com.jorgeaquino.AutoRepuestosGT.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
}