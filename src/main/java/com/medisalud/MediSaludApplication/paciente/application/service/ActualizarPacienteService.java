package com.medisalud.MediSaludApplication.paciente.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.common.exeption.ResourceNotFoundException;
import com.medisalud.MediSaludApplication.paciente.api.dto.request.ActualizarPacienteRequest;
import com.medisalud.MediSaludApplication.paciente.api.dto.response.PacienteResponse;
import com.medisalud.MediSaludApplication.paciente.infraestructure.entity.PacienteEntity;
import com.medisalud.MediSaludApplication.paciente.infraestructure.mapper.PacienteMapper;
import com.medisalud.MediSaludApplication.paciente.infraestructure.repository.JpaPacienteRepository;
@Service
public class ActualizarPacienteService {
private final JpaPacienteRepository repository;
	private final PacienteMapper mapper;

	public ActualizarPacienteService(JpaPacienteRepository repository, PacienteMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public PacienteResponse ejecutar(UUID id, ActualizarPacienteRequest request) {
		PacienteEntity existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
		existing.setFullName(request.getFullName());
		existing.setDocumento(request.getDocumento());
		existing.setPhone(request.getPhone());
		existing.setEmail(request.getEmail());
		var saved = repository.save(existing);
		return mapper.toResponse(saved);
	}
}
