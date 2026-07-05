package com.medisalud.MediSaludApplication.medico.infraestructura.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medisalud.MediSaludApplication.medico.infraestructura.entity.MedicoEntity;

@Repository
public interface JpaMedicoRepository extends JpaRepository<MedicoEntity, UUID> {

}
