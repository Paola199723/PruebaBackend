package com.medisalud.MediSaludApplication.paciente.infraestructure.entity;


import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pacientes")
public class PacienteEntity {
   @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "Documento", nullable = false, length = 100)
    private String documento;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 20)
    private String email;
    public PacienteEntity() {
    }

    public PacienteEntity(UUID id, String fullName, String documento, String phone, String email) {
        this.id = id;
        this.fullName = fullName;
        this.documento = documento  ;
        this.phone = phone;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
