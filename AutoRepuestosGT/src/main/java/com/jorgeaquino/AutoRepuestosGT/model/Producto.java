package com.jorgeaquino.AutoRepuestosGT.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @NotBlank(message = "La categoría del producto no puede estar vacía")
    @Column(name = "categoria_producto")
    private String categoriaProducto;

    @NotNull(message = "El precio de compra no puede ser nulo")
    @Column(name = "precio_compra")
    private BigDecimal precioCompra;

    @NotNull(message = "El precio de venta no puede ser nulo")
    @Column(name = "precio_venta")
    private BigDecimal precioVenta;

    @NotNull(message = "El distribuidor no puede ser nulo")
    @Column(name = "id_distribuidor")
    private Integer idDistribuidor;

    // Getters y Setters
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(Integer idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }
}