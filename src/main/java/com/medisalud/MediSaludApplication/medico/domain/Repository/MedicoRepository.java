package com.medisalud.MediSaludApplication.medico.domain.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.medisalud.MediSaludApplication.medico.domain.model.Medico;

/**
 * Interfaz de repositorio del dominio para `Medico`.
 * Implementaciones (p. ej. adaptadores JPA) deben ubicarse en infraestructura.
 */
public interface MedicoRepository {
    Medico save(Medico medico);
    Optional<Medico> findById(UUID id);
    void deleteById(UUID id);
    List<Medico> findAll();
    List<Medico> findBySpecialty(String specialty);
}
