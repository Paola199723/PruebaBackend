package com.medisalud.MediSaludApplication.medico.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.common.exeption.ResourceNotFoundException;
import com.medisalud.MediSaludApplication.medico.infraestructura.repository.JpaMedicoRepository;

@Service
public class EliminarMedicoService {

	private final JpaMedicoRepository repository;

	public EliminarMedicoService(JpaMedicoRepository repository) {
		this.repository = repository;
	}

	public void ejecutar(UUID id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Médico no encontrado");
		}
		repository.deleteById(id);
	}

}
