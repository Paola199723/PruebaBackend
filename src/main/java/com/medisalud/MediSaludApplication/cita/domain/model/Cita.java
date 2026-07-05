package com.medisalud.MediSaludApplication.cita.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Cita {

	public enum Estado { PROGRAMADA, CANCELADA, ATENDIDA }

	private final UUID id;
	private final UUID pacienteId;
	private final UUID medicoId;
	private final LocalDateTime fechaHora;
	private final Estado estado;
	private final LocalDateTime fechaCancelacion;

	public Cita(UUID id, UUID pacienteId, UUID medicoId, LocalDateTime fechaHora, Estado estado, LocalDateTime fechaCancelacion) {
		if (pacienteId == null) throw new IllegalArgumentException("PacienteId es obligatorio");
		if (medicoId == null) throw new IllegalArgumentException("MedicoId es obligatorio");
		if (fechaHora == null) throw new IllegalArgumentException("Fecha y hora es obligatorio");
		this.id = id;
		this.pacienteId = pacienteId;
		this.medicoId = medicoId;
		this.fechaHora = fechaHora;
		this.estado = (estado == null ? Estado.PROGRAMADA : estado);
		this.fechaCancelacion = fechaCancelacion;
	}

	public UUID getId() { return id; }

	public UUID getPacienteId() { return pacienteId; }

	public UUID getMedicoId() { return medicoId; }

	public LocalDateTime getFechaHora() { return fechaHora; }

	public Estado getEstado() { return estado; }

	public LocalDateTime getFechaCancelacion() { return fechaCancelacion; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cita cita = (Cita) o;
		return Objects.equals(id, cita.id);
	}

	@Override
	public int hashCode() { return Objects.hash(id); }
}
