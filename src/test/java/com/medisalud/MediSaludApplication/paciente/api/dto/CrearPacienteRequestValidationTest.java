package com.medisalud.MediSaludApplication.paciente.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.medisalud.MediSaludApplication.paciente.api.dto.request.CrearPacienteRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class CrearPacienteRequestValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void closeValidator() {
        validatorFactory.close();
    }

    @Test
    void requestValidoNoTieneErrores() {
        CrearPacienteRequest request = new CrearPacienteRequest();
        request.setFullName("Ana María Pérez");
        request.setDocumento("12345678");
        request.setPhone("3001234567");
        request.setEmail("ana.perez@example.com");

        Set<ConstraintViolation<CrearPacienteRequest>> violations = validator.validate(request);
        assertEquals(0, violations.size());
    }

    @Test
    void nombreInvalidoGeneraError() {
        CrearPacienteRequest request = new CrearPacienteRequest();
        request.setFullName("An");
        request.setDocumento("12345678");
        request.setPhone("3001234567");
        request.setEmail("ana.perez@example.com");

        Set<ConstraintViolation<CrearPacienteRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void documentoInvalidoGeneraError() {
        CrearPacienteRequest request = new CrearPacienteRequest();
        request.setFullName("Ana María Pérez");
        request.setDocumento("1234");
        request.setPhone("3001234567");
        request.setEmail("ana.perez@example.com");

        Set<ConstraintViolation<CrearPacienteRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void telefonoInvalidoGeneraError() {
        CrearPacienteRequest request = new CrearPacienteRequest();
        request.setFullName("Ana María Pérez");
        request.setDocumento("12345678");
        request.setPhone("123");
        request.setEmail("ana.perez@example.com");

        Set<ConstraintViolation<CrearPacienteRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emailInvalidoGeneraError() {
        CrearPacienteRequest request = new CrearPacienteRequest();
        request.setFullName("Ana María Pérez");
        request.setDocumento("12345678");
        request.setPhone("3001234567");
        request.setEmail("invalid-email");

        Set<ConstraintViolation<CrearPacienteRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }
}
