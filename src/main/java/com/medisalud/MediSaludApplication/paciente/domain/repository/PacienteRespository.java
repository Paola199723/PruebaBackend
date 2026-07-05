package com.medisalud.MediSaludApplication.paciente.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.medisalud.MediSaludApplication.paciente.domain.model.Paciente;

public interface PacienteRespository {

  Paciente save(Paciente paciente);
    Optional<Paciente> findById(UUID id);
    void deleteById(UUID id);
    List<Paciente> findAll();
    List<Paciente> findBySpecialty(String specialty);
}
