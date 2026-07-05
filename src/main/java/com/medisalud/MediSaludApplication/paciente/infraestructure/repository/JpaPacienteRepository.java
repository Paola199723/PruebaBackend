package com.medisalud.MediSaludApplication.paciente.infraestructure.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medisalud.MediSaludApplication.paciente.infraestructure.entity.PacienteEntity;

@Repository
public interface JpaPacienteRepository extends JpaRepository<PacienteEntity, UUID>  {

}
