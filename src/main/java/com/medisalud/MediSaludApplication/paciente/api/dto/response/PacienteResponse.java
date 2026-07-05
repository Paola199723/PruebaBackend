package com.medisalud.MediSaludApplication.paciente.api.dto.response;
import java.util.UUID;

public class PacienteResponse {
    private UUID id;
    private String fullName;
    private String documento;
    private String phone;
    private String email;

    public PacienteResponse() {
    }
    public PacienteResponse(UUID id, String fullName, String documento, String phone, String email) {
		this.id = id;
		this.fullName = fullName;
		this.documento = documento;
		this.phone = phone;
		this.email = email;
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
