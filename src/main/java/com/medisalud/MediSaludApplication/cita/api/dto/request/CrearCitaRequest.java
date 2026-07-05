
package com.medisalud.MediSaludApplication.cita.api.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

public class CrearCitaRequest {

	private UUID pacienteId;
	private UUID medicoId;
	private LocalDateTime fechaHora;

	public UUID getPacienteId() { return pacienteId; }
	public void setPacienteId(UUID pacienteId) { this.pacienteId = pacienteId; }
	public UUID getMedicoId() { return medicoId; }
	public void setMedicoId(UUID medicoId) { this.medicoId = medicoId; }
	public LocalDateTime getFechaHora() { return fechaHora; }
	public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
