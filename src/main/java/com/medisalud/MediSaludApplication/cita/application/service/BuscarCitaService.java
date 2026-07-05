
package com.medisalud.MediSaludApplication.cita.application.service;

import com.medisalud.MediSaludApplication.cita.domain.model.Cita;
import com.medisalud.MediSaludApplication.cita.infraestructure.repository.CitaRepositoryAdapter;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarCitaService {

	private final CitaRepositoryAdapter repo;

	public BuscarCitaService(CitaRepositoryAdapter repo) { this.repo = repo; }

	public Optional<Cita> buscar(UUID id) { return repo.findById(id); }
}
