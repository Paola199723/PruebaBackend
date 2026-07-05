package com.medisalud.MediSaludApplication.medico.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.common.exeption.ResourceNotFoundException;
import com.medisalud.MediSaludApplication.medico.api.dto.request.ActualizarMedicoRequest;
import com.medisalud.MediSaludApplication.medico.api.dto.response.MedicoResponse;
import com.medisalud.MediSaludApplication.medico.infraestructura.entity.MedicoEntity;
import com.medisalud.MediSaludApplication.medico.infraestructura.mapper.MedicoMapper;
import com.medisalud.MediSaludApplication.medico.infraestructura.repository.JpaMedicoRepository;

@Service
public class ActualizarMedicoService {

	private final JpaMedicoRepository repository;
	private final MedicoMapper mapper;

	public ActualizarMedicoService(JpaMedicoRepository repository, MedicoMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public MedicoResponse ejecutar(UUID id, ActualizarMedicoRequest request) {
		MedicoEntity existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado"));
		existing.setFullName(request.getFullName());
		existing.setSpecialty(request.getSpecialty());
		existing.setPhone(request.getPhone());
		var saved = repository.save(existing);
		return mapper.toResponse(saved);
	}

}
