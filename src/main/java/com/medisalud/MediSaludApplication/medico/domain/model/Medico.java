package com.medisalud.MediSaludApplication.medico.domain.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio `Medico` con validaciones de invariantes.
 */
public class Medico {

    private final UUID id;
    private final String fullName;
    private final String specialty;
    private final String phone;
    private final String email;


    public Medico(UUID id, String fullName, String specialty, String phone, String email) {
        if (fullName == null) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }
        String nameTrim = fullName.trim();
        if (nameTrim.length() < 3 || nameTrim.length() > 100) {
            throw new IllegalArgumentException("El nombre completo debe tener entre 3 y 100 caracteres");
        }
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        if (phone != null) {
            String digitsOnly = phone.replaceAll("\\D", "");
            if (digitsOnly.length() < 7) {
                throw new IllegalArgumentException("El teléfono debe contener al menos 7 dígitos");
            }
        }

        this.id = id;
        this.fullName = nameTrim;
        this.specialty = specialty.trim();
        this.phone = (phone == null ? null : phone.trim());
        this.email = "";
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return Objects.equals(id, medico.id) && Objects.equals(fullName, medico.fullName) && Objects.equals(specialty, medico.specialty) && Objects.equals(phone, medico.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, specialty, phone);
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
