package com.jorgeaquino.AutoRepuestosGT.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Integer idTransaccion;

    @NotNull(message = "La fecha de transacción no puede ser nula")
    @Column(name = "fecha_transaccion")
    private LocalDate fechaTransaccion;

    @NotNull(message = "La cantidad no puede ser nula")
    private Integer cantidad;

    @NotNull(message = "El total no puede ser nulo")
    private BigDecimal total;

    @NotNull(message = "El ID del personal no puede ser nulo")
    @Column(name = "id_personal")
    private Integer idPersonal;

    @NotNull(message = "El ID del producto no puede ser nulo")
    @Column(name = "id_producto")
    private Integer idProducto;

    // Getters y Setters
    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDate getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDate fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}