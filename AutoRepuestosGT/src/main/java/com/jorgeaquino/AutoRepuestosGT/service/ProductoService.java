package com.jorgeaquino.AutoRepuestosGT.service;

import com.jorgeaquino.AutoRepuestosGT.model.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> getAllProductos();
    Producto getProductoById(Integer id);
    Producto saveProducto(Producto producto);
    Producto updateProducto(Integer id, Producto producto);
    void deleteProducto(Integer id);
}