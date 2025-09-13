package com.jorgeaquino.AutoRepuestosGT.service;

import com.jorgeaquino.AutoRepuestosGT.model.Personal;
import java.util.List;

public interface PersonalService {
    List<Personal> getAllPersonal();
    Personal getPersonalById(Integer id);
    Personal savePersonal(Personal personal);
    Personal updatePersonal(Integer id, Personal personal);
    void deletePersonal(Integer id);
}