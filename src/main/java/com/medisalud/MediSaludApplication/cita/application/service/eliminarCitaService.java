
package com.medisalud.MediSaludApplication.cita.application.service;

import com.medisalud.MediSaludApplication.cita.domain.model.Cita;
import com.medisalud.MediSaludApplication.cita.infraestructure.entity.PenalizacionEntity;
import com.medisalud.MediSaludApplication.cita.infraestructure.repository.CitaRepositoryAdapter;
import com.medisalud.MediSaludApplication.cita.infraestructure.repository.JpaPenalizacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class eliminarCitaService {

	private final CitaRepositoryAdapter repo;
	private final JpaPenalizacionRepository penalRepo;

	public eliminarCitaService(CitaRepositoryAdapter repo, JpaPenalizacionRepository penalRepo) {
		this.repo = repo;
		this.penalRepo = penalRepo;
	}

	public Cita cancelar(UUID citaId) {
		Optional<Cita> opt = repo.findById(citaId);
		if (opt.isEmpty()) throw new IllegalArgumentException("Cita no encontrada");
		Cita c = opt.get();
		if (c.getEstado() != Cita.Estado.PROGRAMADA) throw new IllegalStateException("Solo se pueden cancelar citas programadas");

		LocalDateTime now = LocalDateTime.now();
		boolean late = now.isAfter(c.getFechaHora().minusHours(2));

		// create canceled instance
		Cita cancelled = new Cita(c.getId(), c.getPacienteId(), c.getMedicoId(), c.getFechaHora(), Cita.Estado.CANCELADA, now);
		Cita saved = repo.save(cancelled);

		if (late) {
			PenalizacionEntity p = new PenalizacionEntity(java.util.UUID.randomUUID(), c.getPacienteId(), now);
			penalRepo.save(p);
		}

		return saved;
	}
}
