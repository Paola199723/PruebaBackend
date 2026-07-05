package com.medisalud.MediSaludApplication.cita.infraestructure.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "penalizaciones")
public class PenalizacionEntity {

    @Id
    private UUID id;

    private UUID pacienteId;
    private LocalDateTime fecha;

    public PenalizacionEntity() {}

    public PenalizacionEntity(UUID id, UUID pacienteId, LocalDateTime fecha) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.fecha = fecha;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPacienteId() { return pacienteId; }
    public void setPacienteId(UUID pacienteId) { this.pacienteId = pacienteId; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
