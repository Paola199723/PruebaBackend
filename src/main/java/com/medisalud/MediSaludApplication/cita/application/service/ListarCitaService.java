package com.medisalud.MediSaludApplication.cita.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.cita.domain.model.Cita;
import com.medisalud.MediSaludApplication.cita.infraestructure.repository.CitaRepositoryAdapter;

@Service
public class ListarCitaService {

	private final CitaRepositoryAdapter repo;
	private final HorarioAtencionService horarioAtencionService;

	public ListarCitaService(CitaRepositoryAdapter repo, HorarioAtencionService horarioAtencionService) {
		this.repo = repo;
		this.horarioAtencionService = horarioAtencionService;
	}

	public List<Cita> listar(UUID medicoId, UUID pacienteId, String estado, LocalDateTime desde, LocalDateTime hasta) {
		return repo.listAll().stream()
			.filter(c -> medicoId == null || c.getMedicoId().equals(medicoId))
			.filter(c -> pacienteId == null || c.getPacienteId().equals(pacienteId))
			.filter(c -> estado == null || c.getEstado().name().equals(estado))
			.filter(c -> desde == null || !c.getFechaHora().isBefore(desde))
			.filter(c -> hasta == null || !c.getFechaHora().isAfter(hasta))
			.collect(Collectors.toList());
	}

	public java.util.List<java.time.LocalDateTime> disponibles(UUID medicoId, LocalDateTime desde, LocalDateTime hasta) {
		java.util.List<java.time.LocalDateTime> slots = new java.util.ArrayList<>();
		LocalDateTime cursor = desde.truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
		while (cursor.isBefore(hasta)) {
			if (cursor.getMinute() != 0 && cursor.getMinute() != 30) {
				cursor = cursor.plusMinutes(30 - (cursor.getMinute() % 30));
				continue;
			}
			if (horarioAtencionService.isWithinWorkingHours(cursor)) {
				slots.add(cursor);
			}
			cursor = cursor.plusMinutes(30);
		}
		java.util.List<Cita> ocupadas = repo.findProgramadasByMedicoAndRange(medicoId, desde, hasta);
		java.util.Set<LocalDateTime> ocupadasSet = new java.util.HashSet<>();
		for (Cita c : ocupadas) ocupadasSet.add(c.getFechaHora());
		slots.removeIf(ocupadasSet::contains);
		return slots;
	}
}
