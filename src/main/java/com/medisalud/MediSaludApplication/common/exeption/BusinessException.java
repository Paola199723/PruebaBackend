package com.medisalud.MediSaludApplication.common.exeption;

public class BusinessException extends RuntimeException {

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
