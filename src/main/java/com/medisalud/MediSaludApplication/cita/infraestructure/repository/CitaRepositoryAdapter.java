
package com.medisalud.MediSaludApplication.cita.infraestructure.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.medisalud.MediSaludApplication.cita.domain.model.Cita;
import com.medisalud.MediSaludApplication.cita.infraestructure.entity.CitaEntity;
import com.medisalud.MediSaludApplication.cita.infraestructure.mapper.CitaMapper;

@Repository
public class CitaRepositoryAdapter {

	private final JpaCitaRepository jpa;

	public CitaRepositoryAdapter(JpaCitaRepository jpa) { this.jpa = jpa; }

	public Cita save(Cita cita) {
		CitaEntity e = CitaMapper.toEntity(cita);
		if (e.getId() == null) e.setId(UUID.randomUUID());
		CitaEntity saved = jpa.save(e);
		return CitaMapper.toDomain(saved);
	}

	public Optional<Cita> findById(UUID id) {
		return jpa.findById(id).map(CitaMapper::toDomain);
	}

	public List<Cita> findProgramadasByMedicoAndRange(UUID medicoId, LocalDateTime desde, LocalDateTime hasta) {
		List<CitaEntity> list = jpa.findProgramadasByMedicoAndRange(medicoId, desde, hasta);
		return list.stream().map(CitaMapper::toDomain).collect(Collectors.toList());
	}

	public boolean existsConflict(UUID medicoId, LocalDateTime fechaHora) {
		return !jpa.findConflicts(medicoId, fechaHora).isEmpty();
	}

	public boolean existsPacienteConflict(UUID pacienteId, UUID medicoId, LocalDateTime fechaHora) {
		return !jpa.findPacienteConflict(pacienteId, medicoId, fechaHora).isEmpty();
	}

	public List<Cita> listAll() {
		return jpa.findAll().stream().map(CitaMapper::toDomain).collect(Collectors.toList());
	}

	public List<Cita> findByMedico(UUID medicoId) {
		return jpa.findByMedicoId(medicoId).stream().map(CitaMapper::toDomain).collect(Collectors.toList());
	}

	public List<Cita> findByPaciente(UUID pacienteId) {
		return jpa.findByPacienteId(pacienteId).stream().map(CitaMapper::toDomain).collect(Collectors.toList());
	}

	public List<Cita> findByEstado(String estado) {
		return jpa.findByEstado(estado).stream().map(CitaMapper::toDomain).collect(Collectors.toList());
	}

	public List<Cita> findByFechaRange(LocalDateTime desde, LocalDateTime hasta) {
		return jpa.findByFechaHoraBetween(desde, hasta).stream().map(CitaMapper::toDomain).collect(Collectors.toList());
	}
}
