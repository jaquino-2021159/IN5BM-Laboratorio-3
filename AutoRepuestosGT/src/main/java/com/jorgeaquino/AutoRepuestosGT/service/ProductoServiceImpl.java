package com.jorgeaquino.AutoRepuestosGT.service;

import com.jorgeaquino.AutoRepuestosGT.model.Producto;
import com.jorgeaquino.AutoRepuestosGT.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Override
    public Producto saveProducto(Producto producto) {
        if (productoRepository.findByNombreProducto(producto.getNombreProducto()).isPresent()) {
            throw new RuntimeException("El producto '" + producto.getNombreProducto() + "' ya existe");
        }
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Integer id, Producto producto) {
        Producto existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        Optional<Producto> productoWithSameName = productoRepository.findByNombreProducto(producto.getNombreProducto());
        if (productoWithSameName.isPresent() && !productoWithSameName.get().getIdProducto().equals(id)) {
            throw new RuntimeException("El producto '" + producto.getNombreProducto() + "' ya existe");
        }

        existingProducto.setNombreProducto(producto.getNombreProducto());
        existingProducto.setCategoriaProducto(producto.getCategoriaProducto());
        existingProducto.setPrecioVenta(producto.getPrecioVenta());
        existingProducto.setPrecioCompra(producto.getPrecioCompra());
        existingProducto.setIdDistribuidor(producto.getIdDistribuidor());

        return productoRepository.save(existingProducto);
    }

    @Override
    public void deleteProducto(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}