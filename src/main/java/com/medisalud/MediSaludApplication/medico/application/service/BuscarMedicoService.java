package com.medisalud.MediSaludApplication.medico.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.common.exeption.ResourceNotFoundException;
import com.medisalud.MediSaludApplication.medico.api.dto.response.MedicoResponse;
import com.medisalud.MediSaludApplication.medico.infraestructura.mapper.MedicoMapper;
import com.medisalud.MediSaludApplication.medico.infraestructura.repository.JpaMedicoRepository;

@Service
public class BuscarMedicoService {

	private final JpaMedicoRepository repository;
	private final MedicoMapper mapper;

	public BuscarMedicoService(JpaMedicoRepository repository, MedicoMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public MedicoResponse ejecutar(UUID id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado"));
		return mapper.toResponse(entity);
	}

}
