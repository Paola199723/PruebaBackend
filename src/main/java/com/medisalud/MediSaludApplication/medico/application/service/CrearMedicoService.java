package com.medisalud.MediSaludApplication.medico.application.service;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.medico.api.dto.request.CrearMedicoRequest;
import com.medisalud.MediSaludApplication.medico.api.dto.response.MedicoResponse;
import com.medisalud.MediSaludApplication.medico.infraestructura.mapper.MedicoMapper;
import com.medisalud.MediSaludApplication.medico.infraestructura.repository.JpaMedicoRepository;

@Service
public class CrearMedicoService {

	private final JpaMedicoRepository repository;
	private final MedicoMapper mapper;

	public CrearMedicoService(JpaMedicoRepository repository, MedicoMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public MedicoResponse ejecutar(CrearMedicoRequest request) {
		var entity = mapper.toEntity(request);
		var saved = repository.save(entity);
		return mapper.toResponse(saved);
	}

}
