package com.medisalud.MediSaludApplication.paciente.application.service;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.common.exeption.ResourceNotFoundException;
import com.medisalud.MediSaludApplication.paciente.api.dto.response.PacienteResponse;
import com.medisalud.MediSaludApplication.paciente.infraestructure.mapper.PacienteMapper;
import com.medisalud.MediSaludApplication.paciente.infraestructure.repository.JpaPacienteRepository;
@Service
public class BuscarPacienteService {

	private final JpaPacienteRepository repository;
	private final PacienteMapper mapper;

	public BuscarPacienteService(JpaPacienteRepository repository, PacienteMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public PacienteResponse ejecutar(UUID id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
		return mapper.toResponse(entity);
	}
}
