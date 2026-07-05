
package com.medisalud.MediSaludApplication.cita.api.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class CitaResponse {

	private UUID id;
	private UUID pacienteId;
	private UUID medicoId;
	private LocalDateTime fechaHora;
	private String estado;
	private LocalDateTime fechaCancelacion;

	public CitaResponse(UUID id, UUID pacienteId, UUID medicoId, LocalDateTime fechaHora, String estado, LocalDateTime fechaCancelacion) {
		this.id = id;
		this.pacienteId = pacienteId;
		this.medicoId = medicoId;
		this.fechaHora = fechaHora;
		this.estado = estado;
		this.fechaCancelacion = fechaCancelacion;
	}

	public UUID getId() { return id; }
	public UUID getPacienteId() { return pacienteId; }
	public UUID getMedicoId() { return medicoId; }
	public LocalDateTime getFechaHora() { return fechaHora; }
	public String getEstado() { return estado; }
	public LocalDateTime getFechaCancelacion() { return fechaCancelacion; }
}
