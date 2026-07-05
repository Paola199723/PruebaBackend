package com.medisalud.MediSaludApplication.paciente.infraestructure.mapper;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.medisalud.MediSaludApplication.paciente.api.dto.request.ActualizarPacienteRequest;
import com.medisalud.MediSaludApplication.paciente.api.dto.request.CrearPacienteRequest;
import com.medisalud.MediSaludApplication.paciente.api.dto.response.PacienteResponse;
import com.medisalud.MediSaludApplication.paciente.infraestructure.entity.PacienteEntity;

@Component
public class PacienteMapper {
	public PacienteEntity toEntity(CrearPacienteRequest req) {
		return new PacienteEntity(null, req.getFullName(), req.getDocumento(), req.getPhone(), req.getEmail());
	}

	public PacienteEntity toEntity(UUID id, ActualizarPacienteRequest req) {
		return new PacienteEntity(id, req.getFullName(), req.getDocumento(), req.getPhone(), req.getEmail());
	}

	public PacienteResponse toResponse(PacienteEntity e) {
		if (e == null) return null;
        return new PacienteResponse(e.getId(), e.getFullName(), e.getDocumento(), e.getPhone(), e.getEmail());
    }
}
