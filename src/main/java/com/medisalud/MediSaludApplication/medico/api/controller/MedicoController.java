package com.medisalud.MediSaludApplication.medico.api.controller;

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
import com.medisalud.MediSaludApplication.medico.api.dto.request.ActualizarMedicoRequest;
import com.medisalud.MediSaludApplication.medico.api.dto.request.CrearMedicoRequest;
import com.medisalud.MediSaludApplication.medico.api.dto.response.MedicoResponse;
import com.medisalud.MediSaludApplication.medico.application.service.ActualizarMedicoService;
import com.medisalud.MediSaludApplication.medico.application.service.BuscarMedicoService;
import com.medisalud.MediSaludApplication.medico.application.service.CrearMedicoService;
import com.medisalud.MediSaludApplication.medico.application.service.EliminarMedicoService;
import com.medisalud.MediSaludApplication.medico.application.service.ListarMedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

	private final CrearMedicoService crearService;
	private final ListarMedicoService listarService;
	private final BuscarMedicoService buscarService;
	private final ActualizarMedicoService actualizarService;
	private final EliminarMedicoService eliminarService;

	public MedicoController(CrearMedicoService crearService, ListarMedicoService listarService, BuscarMedicoService buscarService, ActualizarMedicoService actualizarService, EliminarMedicoService eliminarService) {
		this.crearService = crearService;
		this.listarService = listarService;
		this.buscarService = buscarService;
		this.actualizarService = actualizarService;
		this.eliminarService = eliminarService;
	}

	@PostMapping
	public ResponseEntity<MedicoResponse> crear(@Valid @RequestBody CrearMedicoRequest request) {
		var resp = crearService.ejecutar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@GetMapping
	public ResponseEntity<List<MedicoResponse>> listar() {
		List<MedicoResponse> lista = listarService.ejecutar();
		if (lista == null || lista.isEmpty()) {
			throw new ResourceNotFoundException("no hay medicos disponibles");
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MedicoResponse> obtener(@PathVariable UUID id) {
		return ResponseEntity.ok(buscarService.ejecutar(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MedicoResponse> actualizar(@PathVariable UUID id, @Valid @RequestBody ActualizarMedicoRequest request) {
		return ResponseEntity.ok(actualizarService.ejecutar(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
		eliminarService.ejecutar(id);
		return ResponseEntity.noContent().build();
	}

}
