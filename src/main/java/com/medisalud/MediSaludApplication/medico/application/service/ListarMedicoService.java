package com.medisalud.MediSaludApplication.medico.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medisalud.MediSaludApplication.medico.api.dto.response.MedicoResponse;
import com.medisalud.MediSaludApplication.medico.infraestructura.mapper.MedicoMapper;
import com.medisalud.MediSaludApplication.medico.infraestructura.repository.JpaMedicoRepository;

@Service
public class ListarMedicoService {

	private final JpaMedicoRepository repository;
	private final MedicoMapper mapper;

	public ListarMedicoService(JpaMedicoRepository repository, MedicoMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<MedicoResponse> ejecutar() {
		return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
	}

}
