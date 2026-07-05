package com.medisalud.MediSaludApplication.cita.infraestructure.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medisalud.MediSaludApplication.cita.infraestructure.entity.PenalizacionEntity;

public interface JpaPenalizacionRepository extends JpaRepository<PenalizacionEntity, UUID> {

    @Query("select p from PenalizacionEntity p where p.pacienteId = :pacienteId and p.fecha >= :desde")
    List<PenalizacionEntity> findSince(@Param("pacienteId") UUID pacienteId, @Param("desde") LocalDateTime desde);
}
