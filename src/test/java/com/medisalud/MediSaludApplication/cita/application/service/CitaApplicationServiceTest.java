package com.medisalud.MediSaludApplication.cita.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.medisalud.MediSaludApplication.cita.domain.model.Cita;
import com.medisalud.MediSaludApplication.cita.infraestructure.entity.PenalizacionEntity;
import com.medisalud.MediSaludApplication.cita.infraestructure.repository.CitaRepositoryAdapter;
import com.medisalud.MediSaludApplication.cita.infraestructure.repository.JpaPenalizacionRepository;

@ExtendWith(MockitoExtension.class)
class CitaApplicationServiceTest {

    @Mock
    private CitaRepositoryAdapter citaRepository;

    @Mock
    private JpaPenalizacionRepository penalizacionRepository;

    private CrearCitaService crearCitaService;
    private ListarCitaService listarCitaService;
    private eliminarCitaService eliminarCitaService;

    @BeforeEach
    void setUp() {
        crearCitaService = new CrearCitaService(citaRepository, penalizacionRepository);
        listarCitaService = new ListarCitaService(citaRepository);
        eliminarCitaService = new eliminarCitaService(citaRepository, penalizacionRepository);
    }

    @Test
    void crearCitaConMedicoOcupadoLanzaError() {
        UUID pacienteId = UUID.randomUUID();
        UUID medicoId = UUID.randomUUID();
        LocalDateTime fechaHora = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);

        when(citaRepository.existsConflict(medicoId, fechaHora)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> crearCitaService.create(pacienteId, medicoId, fechaHora));
    }

    @Test
    void listarCitasConFiltrosCombinadosDevuelveSoloCoincidencias() {
        UUID medicoId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        Cita c1 = new Cita(UUID.randomUUID(), pacienteId, medicoId, LocalDateTime.of(2026, 7, 10, 10, 0), Cita.Estado.PROGRAMADA, null);
        Cita c2 = new Cita(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.of(2026, 7, 10, 11, 0), Cita.Estado.CANCELADA, null);
        Cita c3 = new Cita(UUID.randomUUID(), pacienteId, medicoId, LocalDateTime.of(2026, 7, 11, 10, 0), Cita.Estado.PROGRAMADA, null);

        when(citaRepository.listAll()).thenReturn(List.of(c1, c2, c3));

        List<Cita> result = listarCitaService.listar(medicoId, pacienteId, "PROGRAMADA", LocalDateTime.of(2026, 7, 10, 0, 0), LocalDateTime.of(2026, 7, 10, 23, 59));

        assertEquals(1, result.size());
        assertEquals(c1.getId(), result.get(0).getId());
    }

    @Test
    void disponiblesNoIncluyeTurnosOcupados() {
        UUID medicoId = UUID.randomUUID();
        LocalDateTime desde = LocalDateTime.of(2026, 7, 10, 8, 0);
        LocalDateTime hasta = LocalDateTime.of(2026, 7, 10, 10, 0);
        Cita ocupada = new Cita(UUID.randomUUID(), UUID.randomUUID(), medicoId, LocalDateTime.of(2026, 7, 10, 8, 30), Cita.Estado.PROGRAMADA, null);

        when(citaRepository.findProgramadasByMedicoAndRange(medicoId, desde, hasta)).thenReturn(List.of(ocupada));

        List<LocalDateTime> slots = listarCitaService.disponibles(medicoId, desde, hasta);

        assertEquals(3, slots.size());
        assertFalse(slots.contains(LocalDateTime.of(2026, 7, 10, 8, 30)));
    }

    @Test
    void cancelarCitaProgramadaRegistraPenalizacionSiEsTardia() {
        UUID citaId = UUID.randomUUID();
        LocalDateTime fechaHora = LocalDateTime.now().plusHours(1).withSecond(0).withNano(0);
        Cita cita = new Cita(citaId, UUID.randomUUID(), UUID.randomUUID(), fechaHora, Cita.Estado.PROGRAMADA, null);

        when(citaRepository.findById(citaId)).thenReturn(Optional.of(cita));
        when(citaRepository.save(any(Cita.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cita result = eliminarCitaService.cancelar(citaId);

        assertEquals(Cita.Estado.CANCELADA, result.getEstado());
        verify(penalizacionRepository).save(any(PenalizacionEntity.class));
    }
}
