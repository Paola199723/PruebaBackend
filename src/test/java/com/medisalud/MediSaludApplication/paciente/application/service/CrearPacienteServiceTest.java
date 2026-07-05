package com.medisalud.MediSaludApplication.paciente.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.medisalud.MediSaludApplication.paciente.api.dto.request.CrearPacienteRequest;
import com.medisalud.MediSaludApplication.paciente.api.dto.response.PacienteResponse;
import com.medisalud.MediSaludApplication.paciente.infraestructure.entity.PacienteEntity;
import com.medisalud.MediSaludApplication.paciente.infraestructure.mapper.PacienteMapper;
import com.medisalud.MediSaludApplication.paciente.infraestructure.repository.JpaPacienteRepository;

@ExtendWith(MockitoExtension.class)
class CrearPacienteServiceTest {

    @Mock
    private JpaPacienteRepository repository;

    @Mock
    private PacienteMapper mapper;

    private CrearPacienteService service;

    @BeforeEach
    void setUp() {
        service = new CrearPacienteService(repository, mapper);
    }

    @Test
    void crearPacienteConDocumentoDuplicadoLanzaError() {
        CrearPacienteRequest request = new CrearPacienteRequest();
        request.setFullName("Ana Pérez");
        request.setDocumento("12345678");
        request.setPhone("3001234567");
        request.setEmail("ana.perez@example.com");

        when(repository.findByDocumento("12345678")).thenReturn(Optional.of(new PacienteEntity()));

        assertThrows(IllegalStateException.class, () -> service.ejecutar(request));
    }

    @Test
    void crearPacienteValidoGuardaYRetornaRespuesta() {
        CrearPacienteRequest request = new CrearPacienteRequest();
        request.setFullName("Ana Pérez");
        request.setDocumento("12345678");
        request.setPhone("3001234567");
        request.setEmail("ana.perez@example.com");

        PacienteEntity entity = new PacienteEntity(null, "Ana Pérez", "12345678", "3001234567", "ana.perez@example.com");
        PacienteEntity savedEntity = new PacienteEntity(java.util.UUID.randomUUID(), "Ana Pérez", "12345678", "3001234567", "ana.perez@example.com");
        PacienteResponse response = new PacienteResponse(savedEntity.getId(), savedEntity.getFullName(), savedEntity.getDocumento(), savedEntity.getPhone(), savedEntity.getEmail());

        when(repository.findByDocumento("12345678")).thenReturn(Optional.empty());
        when(mapper.toEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toResponse(savedEntity)).thenReturn(response);

        PacienteResponse result = service.ejecutar(request);
        assertEquals(response.getId(), result.getId());
        assertEquals(response.getDocumento(), result.getDocumento());
        assertEquals(response.getPhone(), result.getPhone());
        assertEquals(response.getEmail(), result.getEmail());
    }
}
