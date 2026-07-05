package com.medisalud.MediSaludApplication.paciente.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Paciente {

   private final UUID id;
    private final String fullName;
    private final String Documento;
    private final String phone;
    private final String email;


    public Paciente(UUID id, String fullName, String Documento , String phone, String email) {
        if (fullName == null) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }
        String nameTrim = fullName.trim();
        if (nameTrim.length() < 3 || nameTrim.length() > 100) {
            throw new IllegalArgumentException("El nombre completo debe tener entre 3 y 100 caracteres");
        }
        if (Documento == null || Documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }
        if (phone != null) {
            String digitsOnly = phone.replaceAll("\\D", "");
            if (digitsOnly.length() < 7) {
                throw new IllegalArgumentException("El teléfono debe contener al menos 7 dígitos");
            }
        }

        this.id = id;
        this.fullName = nameTrim;
        this.Documento = Documento.trim();
        this.phone = (phone == null ? null : phone.trim());
        this.email = "";
    
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDocumento() {
        return Documento;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id) && Objects.equals(fullName, paciente.fullName) && Objects.equals(Documento, paciente.Documento) && Objects.equals(phone, paciente.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, Documento, phone);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", Documento='" + Documento + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
