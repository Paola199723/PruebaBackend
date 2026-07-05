
package com.medisalud.MediSaludApplication.cita.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medisalud.MediSaludApplication.cita.api.dto.request.CrearCitaRequest;
import com.medisalud.MediSaludApplication.cita.api.dto.response.CitaResponse;
import com.medisalud.MediSaludApplication.cita.application.service.BuscarCitaService;
import com.medisalud.MediSaludApplication.cita.application.service.CrearCitaService;
import com.medisalud.MediSaludApplication.cita.application.service.ListarCitaService;
import com.medisalud.MediSaludApplication.cita.application.service.eliminarCitaService;
import com.medisalud.MediSaludApplication.cita.infraestructure.mapper.CitaMapper;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

	private final CrearCitaService crear;
	private final ListarCitaService listar;
	private final BuscarCitaService buscar;
	private final eliminarCitaService eliminar;

	public CitaController(CrearCitaService crear, ListarCitaService listar, BuscarCitaService buscar, eliminarCitaService eliminar) {
		this.crear = crear; this.listar = listar; this.buscar = buscar; this.eliminar = eliminar;
	}

	@PostMapping
	public ResponseEntity<CitaResponse> crear(@RequestBody CrearCitaRequest req) {
		var cita = crear.create(req.getPacienteId(), req.getMedicoId(), req.getFechaHora());
		var resp = CitaMapper.toResponse(CitaMapper.toEntity(cita));
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/available")
	public ResponseEntity<List<LocalDateTime>> disponibles(@RequestParam UUID medicoId,
														   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
														   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
		List<LocalDateTime> slots = listar.disponibles(medicoId, fechaInicio, fechaFin);
		return ResponseEntity.ok(slots);
	}

	@PostMapping("/{id}/cancel")
	public ResponseEntity<CitaResponse> cancelar(@PathVariable UUID id) {
		var c = eliminar.cancelar(id);
		var resp = CitaMapper.toResponse(CitaMapper.toEntity(c));
		return ResponseEntity.ok(resp);
	}

	@GetMapping
	public ResponseEntity<List<CitaResponse>> listar(@RequestParam(required = false) UUID medicoId,
													 @RequestParam(required = false) UUID pacienteId,
													 @RequestParam(required = false) String estado,
													 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
													 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
		var list = listar.listar(medicoId, pacienteId, estado, fechaInicio, fechaFin);
		List<CitaResponse> res = list.stream().map(c -> CitaMapper.toResponse(CitaMapper.toEntity(c))).collect(Collectors.toList());
		return ResponseEntity.ok(res);
	}
}
