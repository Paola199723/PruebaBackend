package com.medisalud.MediSaludApplication.paciente.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.common.exeption.ResourceNotFoundException;
import com.medisalud.MediSaludApplication.paciente.infraestructure.repository.JpaPacienteRepository;

@Service
public class EliminarPacienteService {

	private final JpaPacienteRepository repository;

	public EliminarPacienteService(JpaPacienteRepository repository) {
		this.repository = repository;
	}

	public void ejecutar(UUID id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Paciente no encontrado");
		}
		repository.deleteById(id);
	}
}
