package com.medisalud.MediSaludApplication.cita.application.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.cita.domain.model.Cita;
import com.medisalud.MediSaludApplication.cita.infraestructure.repository.CitaRepositoryAdapter;
import com.medisalud.MediSaludApplication.cita.infraestructure.repository.JpaPenalizacionRepository;

@Service
public class CrearCitaService {

	private final CitaRepositoryAdapter repo;
	private final JpaPenalizacionRepository penalRepo;
	private final HorarioAtencionService horarioAtencionService;

	public CrearCitaService(CitaRepositoryAdapter repo, JpaPenalizacionRepository penalRepo, HorarioAtencionService horarioAtencionService) {
		this.repo = repo;
		this.penalRepo = penalRepo;
		this.horarioAtencionService = horarioAtencionService;
	}

	public Cita create(UUID pacienteId, UUID medicoId, LocalDateTime fechaHora) {
		// Validaciones básicas
		if (fechaHora == null) throw new IllegalArgumentException("fechaHora es obligatorio");
		// Slot must be aligned to 30 minutes
		int minute = fechaHora.getMinute();
		if (!(minute == 0 || minute == 30)) throw new IllegalArgumentException("La franja debe empezar en minuto 00 o 30");

		// RN-01 horarios
		if (!horarioAtencionService.isWithinWorkingHours(fechaHora)) throw new IllegalArgumentException("Horario fuera del horario laboral del médico");

		// RN-05 Penalizaciones
		long sinceDays = 30;
		long penalties = penalRepo.findSince(pacienteId, LocalDateTime.now().minusDays(sinceDays)).size();
		if (penalties >= 3) throw new IllegalStateException("Paciente bloqueado por 3 o más penalizaciones en los últimos 30 días");

		// RN-02 duplicidad médico
		if (repo.existsConflict(medicoId, fechaHora)) throw new IllegalStateException("El médico ya tiene una cita en esa franja");

		// RN-04 conflicto paciente
		if (repo.existsPacienteConflict(pacienteId, medicoId, fechaHora)) throw new IllegalStateException("El paciente ya tiene una cita con ese médico en esa franja");

		Cita cita = new Cita(null, pacienteId, medicoId, fechaHora.truncatedTo(ChronoUnit.MINUTES), Cita.Estado.PROGRAMADA, null);
		return repo.save(cita);
	}
}
