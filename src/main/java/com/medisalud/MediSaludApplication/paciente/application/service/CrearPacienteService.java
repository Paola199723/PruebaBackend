package com.medisalud.MediSaludApplication.paciente.application.service;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.paciente.api.dto.request.CrearPacienteRequest;
import com.medisalud.MediSaludApplication.paciente.api.dto.response.PacienteResponse;
import com.medisalud.MediSaludApplication.paciente.infraestructure.mapper.PacienteMapper;
import com.medisalud.MediSaludApplication.paciente.infraestructure.repository.JpaPacienteRepository;

@Service
public class CrearPacienteService {

	private final JpaPacienteRepository repository;
	private final PacienteMapper mapper;

	public CrearPacienteService(JpaPacienteRepository repository, PacienteMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public PacienteResponse ejecutar(CrearPacienteRequest request) {
		var entity = mapper.toEntity(request);
		var saved = repository.save(entity);
		return mapper.toResponse(saved);
	}
}
