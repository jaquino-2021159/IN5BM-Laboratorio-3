package com.jorgeaquino.AutoRepuestosGT.service;

import com.jorgeaquino.AutoRepuestosGT.model.Transaccion;
import java.util.List;

public interface TransaccionService {
    List<Transaccion> getAllTransacciones();
    Transaccion getTransaccionById(Integer id);
    Transaccion saveTransaccion(Transaccion transaccion);
    Transaccion updateTransaccion(Integer id, Transaccion transaccion);
    void deleteTransaccion(Integer id);
}