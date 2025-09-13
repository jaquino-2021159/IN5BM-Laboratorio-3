package com.jorgeaquino.AutoRepuestosGT.service;

import com.jorgeaquino.AutoRepuestosGT.model.Producto;
import com.jorgeaquino.AutoRepuestosGT.model.Transaccion;
import com.jorgeaquino.AutoRepuestosGT.repository.PersonalRepository;
import com.jorgeaquino.AutoRepuestosGT.repository.ProductoRepository;
import com.jorgeaquino.AutoRepuestosGT.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final PersonalRepository personalRepository;
    private final ProductoRepository productoRepository;

    public TransaccionServiceImpl(TransaccionRepository transaccionRepository, PersonalRepository personalRepository, ProductoRepository productoRepository) {
        this.transaccionRepository = transaccionRepository;
        this.personalRepository = personalRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Transaccion> getAllTransacciones() {
        return transaccionRepository.findAll();
    }

    @Override
    public Transaccion getTransaccionById(Integer id) {
        return transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada con ID: " + id));
    }

    @Override
    public Transaccion saveTransaccion(Transaccion transaccion) {
        if (!personalRepository.existsById(transaccion.getIdPersonal())) {
            throw new RuntimeException("El personal con ID " + transaccion.getIdPersonal() + " no existe");
        }
        Producto producto = productoRepository.findById(transaccion.getIdProducto())
                .orElseThrow(() -> new RuntimeException("El producto con ID " + transaccion.getIdProducto() + " no existe"));

        transaccion.setTotal(producto.getPrecioVenta().multiply(BigDecimal.valueOf(transaccion.getCantidad())));

        return transaccionRepository.save(transaccion);
    }

    @Override
    public Transaccion updateTransaccion(Integer id, Transaccion transaccion) {
        Transaccion existingTransaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada con ID: " + id));

        if (!personalRepository.existsById(transaccion.getIdPersonal())) {
            throw new RuntimeException("El personal con ID " + transaccion.getIdPersonal() + " no existe");
        }
        Producto producto = productoRepository.findById(transaccion.getIdProducto())
                .orElseThrow(() -> new RuntimeException("El producto con ID " + transaccion.getIdProducto() + " no existe"));

        existingTransaccion.setCantidad(transaccion.getCantidad());
        existingTransaccion.setFechaTransaccion(transaccion.getFechaTransaccion());
        existingTransaccion.setIdPersonal(transaccion.getIdPersonal());
        existingTransaccion.setIdProducto(transaccion.getIdProducto());

        existingTransaccion.setTotal(producto.getPrecioVenta().multiply(BigDecimal.valueOf(existingTransaccion.getCantidad())));

        return transaccionRepository.save(existingTransaccion);
    }

    @Override
    public void deleteTransaccion(Integer id) {
        if (!transaccionRepository.existsById(id)) {
            throw new RuntimeException("Transacción no encontrada con ID: " + id);
        }
        transaccionRepository.deleteById(id);
    }
}