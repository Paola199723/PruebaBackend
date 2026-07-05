
package com.medisalud.MediSaludApplication.cita.infraestructure.mapper;

import java.util.UUID;

import com.medisalud.MediSaludApplication.cita.api.dto.response.CitaResponse;
import com.medisalud.MediSaludApplication.cita.domain.model.Cita;
import com.medisalud.MediSaludApplication.cita.infraestructure.entity.CitaEntity;

public class CitaMapper {

	public static CitaEntity toEntity(Cita cita) {
		UUID id = cita.getId();
		return new CitaEntity(id, cita.getPacienteId(), cita.getMedicoId(), cita.getFechaHora(), cita.getEstado().name(), cita.getFechaCancelacion());
	}

	public static Cita toDomain(CitaEntity e) {
		return new Cita(e.getId(), e.getPacienteId(), e.getMedicoId(), e.getFechaHora(), Cita.Estado.valueOf(e.getEstado()), e.getFechaCancelacion());
	}

	public static CitaResponse toResponse(CitaEntity e) {
		return new CitaResponse(e.getId(), e.getPacienteId(), e.getMedicoId(), e.getFechaHora(), e.getEstado(), e.getFechaCancelacion());
	}
}
