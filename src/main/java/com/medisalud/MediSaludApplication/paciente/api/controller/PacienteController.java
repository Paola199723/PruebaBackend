package com.medisalud.MediSaludApplication.paciente.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medisalud.MediSaludApplication.common.exeption.ResourceNotFoundException;
import com.medisalud.MediSaludApplication.paciente.api.dto.request.ActualizarPacienteRequest;
import com.medisalud.MediSaludApplication.paciente.api.dto.request.CrearPacienteRequest;
import com.medisalud.MediSaludApplication.paciente.api.dto.response.PacienteResponse;
import com.medisalud.MediSaludApplication.paciente.application.service.ActualizarPacienteService;
import com.medisalud.MediSaludApplication.paciente.application.service.BuscarPacienteService;
import com.medisalud.MediSaludApplication.paciente.application.service.CrearPacienteService;
import com.medisalud.MediSaludApplication.paciente.application.service.EliminarPacienteService;
import com.medisalud.MediSaludApplication.paciente.application.service.ListarPacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
	private final CrearPacienteService crearService;
	private final ListarPacienteService listarService;
	private final BuscarPacienteService buscarService;
	private final ActualizarPacienteService actualizarService;
	private final EliminarPacienteService eliminarService;

	public PacienteController(CrearPacienteService crearService, ListarPacienteService listarService, BuscarPacienteService buscarService, ActualizarPacienteService actualizarService, EliminarPacienteService eliminarService) {
		this.crearService = crearService;
		this.listarService = listarService;
		
		this.buscarService = buscarService;
		this.actualizarService = actualizarService;
		this.eliminarService = eliminarService;
	}

	@PostMapping
	public ResponseEntity<PacienteResponse> crear(@Valid @RequestBody CrearPacienteRequest request) {
		var resp = crearService.ejecutar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@GetMapping
	public ResponseEntity<List<PacienteResponse>> listar() {
		List<PacienteResponse> lista = listarService.ejecutar();
		if (lista == null || lista.isEmpty()) {
			throw new ResourceNotFoundException("no hay pacientes disponibles");
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PacienteResponse> obtener(@PathVariable UUID id) {
		return ResponseEntity.ok(buscarService.ejecutar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PacienteResponse> actualizar(@PathVariable UUID id, @Valid @RequestBody ActualizarPacienteRequest request) {
		return ResponseEntity.ok(actualizarService.ejecutar(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
		eliminarService.ejecutar(id);
		return ResponseEntity.noContent().build();
	}
}
