package com.medisalud.MediSaludApplication.medico.api.dto.response;

import java.util.UUID;

public class MedicoResponse {

	private UUID id;
	private String fullName;
	private String specialty;
	private String phone;

	public MedicoResponse() {
	}

	public MedicoResponse(UUID id, String fullName, String specialty, String phone) {
		this.id = id;
		this.fullName = fullName;
		this.specialty = specialty;
		this.phone = phone;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
