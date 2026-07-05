
package com.medisalud.MediSaludApplication.cita.infraestructure.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medisalud.MediSaludApplication.cita.infraestructure.entity.CitaEntity;

public interface JpaCitaRepository extends JpaRepository<CitaEntity, UUID> {

	@Query("select c from CitaEntity c where c.medicoId = :medicoId and c.fechaHora >= :desde and c.fechaHora < :hasta and c.estado = 'PROGRAMADA'")
	List<CitaEntity> findProgramadasByMedicoAndRange(@Param("medicoId") UUID medicoId, @Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);

	@Query("select c from CitaEntity c where c.medicoId = :medicoId and c.fechaHora = :fechaHora and c.estado = 'PROGRAMADA'")
	List<CitaEntity> findConflicts(@Param("medicoId") UUID medicoId, @Param("fechaHora") LocalDateTime fechaHora);

	@Query("select c from CitaEntity c where c.pacienteId = :pacienteId and c.medicoId = :medicoId and c.fechaHora = :fechaHora and c.estado = 'PROGRAMADA'")
	List<CitaEntity> findPacienteConflict(@Param("pacienteId") UUID pacienteId, @Param("medicoId") UUID medicoId, @Param("fechaHora") LocalDateTime fechaHora);

	List<CitaEntity> findByMedicoId(UUID medicoId);
	List<CitaEntity> findByPacienteId(UUID pacienteId);
	List<CitaEntity> findByEstado(String estado);
	List<CitaEntity> findByFechaHoraBetween(LocalDateTime desde, LocalDateTime hasta);
}
