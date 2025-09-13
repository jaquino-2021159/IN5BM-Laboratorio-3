package com.jorgeaquino.AutoRepuestosGT.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "distribuidores")
public class Distribuidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_distribuidor")
    private Integer idDistribuidor;

    @NotBlank(message = "El nombre del distribuidor no puede estar vacío")
    @Column(name = "nombre_distribuidor")
    private String nombreDistribuidor;

    @Pattern(regexp = "\\d{8}", message = "El teléfono debe contener 8 dígitos")
    @Column(name = "telefono_distribuidor")
    private String telefonoDistribuidor;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @Email(message = "El formato del email es inválido")
    @NotBlank(message = "El email no puede estar vacío")
    @Column(name = "email_distribuidor")
    private String emailDistribuidor;

    // Getters y Setters
    public Integer getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(Integer idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

    public String getTelefonoDistribuidor() {
        return telefonoDistribuidor;
    }

    public void setTelefonoDistribuidor(String telefonoDistribuidor) {
        this.telefonoDistribuidor = telefonoDistribuidor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmailDistribuidor() {
        return emailDistribuidor;
    }

    public void setEmailDistribuidor(String emailDistribuidor) {
        this.emailDistribuidor = emailDistribuidor;
    }
}