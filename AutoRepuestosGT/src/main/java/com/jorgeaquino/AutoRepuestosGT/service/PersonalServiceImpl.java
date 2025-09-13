package com.jorgeaquino.AutoRepuestosGT.service;

import com.jorgeaquino.AutoRepuestosGT.model.Personal;
import com.jorgeaquino.AutoRepuestosGT.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalServiceImpl(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    @Override
    public List<Personal> getAllPersonal() {
        return personalRepository.findAll();
    }

    @Override
    public Personal getPersonalById(Integer id) {
        return personalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado con ID: " + id));
    }

    @Override
    public Personal savePersonal(Personal personal) {
        if (personalRepository.findByEmailPersonal(personal.getEmailPersonal()).isPresent()) {
            throw new RuntimeException("El email '" + personal.getEmailPersonal() + "' ya está registrado");
        }
        return personalRepository.save(personal);
    }

    @Override
    public Personal updatePersonal(Integer id, Personal personal) {
        Personal existingPersonal = personalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado con ID: " + id));

        Optional<Personal> personalWithSameEmail = personalRepository.findByEmailPersonal(personal.getEmailPersonal());
        if (personalWithSameEmail.isPresent() && !personalWithSameEmail.get().getIdPersonal().equals(id)) {
            throw new RuntimeException("El email '" + personal.getEmailPersonal() + "' ya está registrado por otro personal");
        }

        existingPersonal.setNombrePersonal(personal.getNombrePersonal());
        existingPersonal.setApellidoPersonal(personal.getApellidoPersonal());
        existingPersonal.setPuestoPersonal(personal.getPuestoPersonal());
        existingPersonal.setEmailPersonal(personal.getEmailPersonal());

        return personalRepository.save(existingPersonal);
    }

    @Override
    public void deletePersonal(Integer id) {
        if (!personalRepository.existsById(id)) {
            throw new RuntimeException("Personal no encontrado con ID: " + id);
        }
        personalRepository.deleteById(id);
    }
}