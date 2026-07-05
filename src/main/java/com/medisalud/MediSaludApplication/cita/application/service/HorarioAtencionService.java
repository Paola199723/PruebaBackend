package com.medisalud.MediSaludApplication.cita.application.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class HorarioAtencionService {

	private static final Set<MonthDay> FESTIVOS = Set.of(
		MonthDay.of(1, 1),
		MonthDay.of(5, 1),
		MonthDay.of(7, 20),
		MonthDay.of(8, 7),
		MonthDay.of(10, 12),
		MonthDay.of(11, 1),
		MonthDay.of(11, 11),
		MonthDay.of(12, 8),
		MonthDay.of(12, 25)
	);

	public boolean isWithinWorkingHours(LocalDateTime fechaHora) {
		if (fechaHora == null) {
			return false;
		}

		LocalDate fecha = fechaHora.toLocalDate();
		if (isHoliday(fecha)) {
			return false;
		}

		DayOfWeek dia = fecha.getDayOfWeek();
		int hora = fechaHora.getHour();

		if (dia == DayOfWeek.SUNDAY) {
			return false;
		}
		if (dia == DayOfWeek.SATURDAY) {
			return hora >= 8 && hora < 13;
		}
		return hora >= 8 && hora < 18;
	}

	public boolean isHoliday(LocalDate fecha) {
		return FESTIVOS.contains(MonthDay.from(fecha));
	}
}
