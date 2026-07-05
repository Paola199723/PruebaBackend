package com.medisalud.MediSaludApplication.medico.infraestructura.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.medisalud.MediSaludApplication.medico.api.dto.request.ActualizarMedicoRequest;
import com.medisalud.MediSaludApplication.medico.api.dto.request.CrearMedicoRequest;
import com.medisalud.MediSaludApplication.medico.api.dto.response.MedicoResponse;
import com.medisalud.MediSaludApplication.medico.infraestructura.entity.MedicoEntity;

@Component
public class MedicoMapper {

	public MedicoEntity toEntity(CrearMedicoRequest req) {
		return new MedicoEntity(null, req.getFullName(), req.getSpecialty(), req.getPhone());
	}

	public MedicoEntity toEntity(UUID id, ActualizarMedicoRequest req) {
		return new MedicoEntity(id, req.getFullName(), req.getSpecialty(), req.getPhone());
	}

	public MedicoResponse toResponse(MedicoEntity e) {
		if (e == null) return null;
		return new MedicoResponse(e.getId(), e.getFullName(), e.getSpecialty(), e.getPhone());
	}

}
