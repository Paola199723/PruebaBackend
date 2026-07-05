package com.medisalud.MediSaludApplication.medico.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ActualizarMedicoRequest {

	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
	private String fullName;

	@NotBlank(message = "La especialidad es obligatoria")
	private String specialty;

	@Pattern(regexp = "(^$|\\d{7,})", message = "El teléfono debe tener al menos 7 dígitos si se proporciona")
	private String phone;

	public ActualizarMedicoRequest() {
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
