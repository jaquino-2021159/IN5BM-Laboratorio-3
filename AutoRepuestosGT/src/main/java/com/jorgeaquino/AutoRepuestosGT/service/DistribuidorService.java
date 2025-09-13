package com.jorgeaquino.AutoRepuestosGT.service;

import com.jorgeaquino.AutoRepuestosGT.model.Distribuidor;
import java.util.List;

public interface DistribuidorService {
    List<Distribuidor> getAllDistribuidores();
    Distribuidor getDistribuidorById(Integer id);
    Distribuidor saveDistribuidor(Distribuidor distribuidor);
    Distribuidor updateDistribuidor(Integer id, Distribuidor distribuidor);
    void deleteDistribuidor(Integer id);
}