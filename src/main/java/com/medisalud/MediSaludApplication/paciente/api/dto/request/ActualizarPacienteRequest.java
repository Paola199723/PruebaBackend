package com.medisalud.MediSaludApplication.paciente.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
public class ActualizarPacienteRequest {

    @NotBlank(message = "El nombre es obligatorio")
	@Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
	private String fullName;

	@NotBlank(message = "El documento es obligatorio")
	@Size(min = 7, max = 15, message = "El documento debe tener entre 7 y 15 caracteres")
	private String documento;

	@Pattern(regexp = "(^$|\\d{7,})", message = "El teléfono debe tener al menos 7 dígitos si se proporciona")
	private String phone;

	@Pattern(regexp = "(^$|\\d{7,})", message = "El teléfono debe tener al menos 7 dígitos si se proporciona")
	private String email;

	public ActualizarPacienteRequest() {
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
