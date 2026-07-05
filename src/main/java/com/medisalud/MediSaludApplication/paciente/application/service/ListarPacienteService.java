package com.medisalud.MediSaludApplication.paciente.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.paciente.api.dto.response.PacienteResponse;
import com.medisalud.MediSaludApplication.paciente.infraestructure.mapper.PacienteMapper;
import com.medisalud.MediSaludApplication.paciente.infraestructure.repository.JpaPacienteRepository;

@Service
public class ListarPacienteService {

    	private final JpaPacienteRepository repository;
	private final PacienteMapper mapper;

	public ListarPacienteService(JpaPacienteRepository repository, PacienteMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<PacienteResponse> ejecutar() {
		return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
	}
}
