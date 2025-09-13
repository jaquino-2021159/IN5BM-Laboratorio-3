package com.jorgeaquino.AutoRepuestosGT.service;

import com.jorgeaquino.AutoRepuestosGT.model.Distribuidor;
import com.jorgeaquino.AutoRepuestosGT.repository.DistribuidorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistribuidorServiceImpl implements DistribuidorService {

    private final DistribuidorRepository distribuidorRepository;

    public DistribuidorServiceImpl(DistribuidorRepository distribuidorRepository) {
        this.distribuidorRepository = distribuidorRepository;
    }

    @Override
    public List<Distribuidor> getAllDistribuidores() {
        return distribuidorRepository.findAll();
    }

    @Override
    public Distribuidor getDistribuidorById(Integer id) {
        return distribuidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Distribuidor no encontrado con ID: " + id));
    }

    @Override
    public Distribuidor saveDistribuidor(Distribuidor distribuidor) {
        if (distribuidorRepository.findByEmailDistribuidor(distribuidor.getEmailDistribuidor()).isPresent()) {
            throw new RuntimeException("El email '" + distribuidor.getEmailDistribuidor() + "' ya está en uso");
        }
        if (distribuidorRepository.findByNombreDistribuidor(distribuidor.getNombreDistribuidor()).isPresent()) {
            throw new RuntimeException("El nombre del distribuidor '" + distribuidor.getNombreDistribuidor() + "' ya está registrado");
        }
        return distribuidorRepository.save(distribuidor);
    }

    @Override
    public Distribuidor updateDistribuidor(Integer id, Distribuidor distribuidor) {
        Distribuidor existingDistribuidor = distribuidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Distribuidor no encontrado con ID: " + id));

        Optional<Distribuidor> distribuidorWithSameEmail = distribuidorRepository.findByEmailDistribuidor(distribuidor.getEmailDistribuidor());
        if (distribuidorWithSameEmail.isPresent() && !distribuidorWithSameEmail.get().getIdDistribuidor().equals(id)) {
            throw new RuntimeException("El email '" + distribuidor.getEmailDistribuidor() + "' ya está en uso por otro distribuidor");
        }

        Optional<Distribuidor> distribuidorWithSameName = distribuidorRepository.findByNombreDistribuidor(distribuidor.getNombreDistribuidor());
        if (distribuidorWithSameName.isPresent() && !distribuidorWithSameName.get().getIdDistribuidor().equals(id)) {
            throw new RuntimeException("El nombre del distribuidor '" + distribuidor.getNombreDistribuidor() + "' ya está registrado por otro distribuidor");
        }

        existingDistribuidor.setNombreDistribuidor(distribuidor.getNombreDistribuidor());
        existingDistribuidor.setTelefonoDistribuidor(distribuidor.getTelefonoDistribuidor());
        existingDistribuidor.setDireccion(distribuidor.getDireccion());
        existingDistribuidor.setEmailDistribuidor(distribuidor.getEmailDistribuidor());
        return distribuidorRepository.save(existingDistribuidor);
    }

    @Override
    public void deleteDistribuidor(Integer id) {
        if (!distribuidorRepository.existsById(id)) {
            throw new RuntimeException("Distribuidor no encontrado con ID: " + id);
        }
        distribuidorRepository.deleteById(id);
    }
}