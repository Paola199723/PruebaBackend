
package com.medisalud.MediSaludApplication.cita.infraestructure.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "citas")
public class CitaEntity {

	@Id
	private UUID id;

	@Column(nullable = false)
	private UUID pacienteId;

	@Column(nullable = false)
	private UUID medicoId;

	@Column(nullable = false)
	private LocalDateTime fechaHora;

	@Column(nullable = false)
	private String estado;

	private LocalDateTime fechaCancelacion;

	public CitaEntity() {}

	public CitaEntity(UUID id, UUID pacienteId, UUID medicoId, LocalDateTime fechaHora, String estado, LocalDateTime fechaCancelacion) {
		this.id = id;
		this.pacienteId = pacienteId;
		this.medicoId = medicoId;
		this.fechaHora = fechaHora;
		this.estado = estado;
		this.fechaCancelacion = fechaCancelacion;
	}

	public UUID getId() { return id; }
	public void setId(UUID id) { this.id = id; }
	public UUID getPacienteId() { return pacienteId; }
	public void setPacienteId(UUID pacienteId) { this.pacienteId = pacienteId; }
	public UUID getMedicoId() { return medicoId; }
	public void setMedicoId(UUID medicoId) { this.medicoId = medicoId; }
	public LocalDateTime getFechaHora() { return fechaHora; }
	public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
	public String getEstado() { return estado; }
	public void setEstado(String estado) { this.estado = estado; }
	public LocalDateTime getFechaCancelacion() { return fechaCancelacion; }
	public void setFechaCancelacion(LocalDateTime fechaCancelacion) { this.fechaCancelacion = fechaCancelacion; }
}
