package com.jorgeaquino.AutoRepuestosGT.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private Integer idPersonal;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "nombre_personal")
    private String nombrePersonal;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(name = "apellido_personal")
    private String apellidoPersonal;

    @NotBlank(message = "El puesto no puede estar vacío")
    @Column(name = "puesto_personal")
    private String puestoPersonal;

    @Email(message = "El formato del email es inválido")
    @NotBlank(message = "El email no puede estar vacío")
    @Column(name = "email_personal")
    private String emailPersonal;

    // Getters y Setters
    public Integer getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }

    public String getApellidoPersonal() {
        return apellidoPersonal;
    }

    public void setApellidoPersonal(String apellidoPersonal) {
        this.apellidoPersonal = apellidoPersonal;
    }

    public String getPuestoPersonal() {
        return puestoPersonal;
    }

    public void setPuestoPersonal(String puestoPersonal) {
        this.puestoPersonal = puestoPersonal;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }
}