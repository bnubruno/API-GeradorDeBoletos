package br.com.desafio.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Fine {

	public static BigDecimal of(BigDecimal value, LocalDate dueDate, LocalDate actualDate) {
		BigDecimal days = new BigDecimal(ChronoUnit.DAYS.between(dueDate, actualDate));

		boolean isExpired = days.compareTo(BigDecimal.ZERO) > 0;
		if (!isExpired) {
			return BigDecimal.ZERO;
		}

		if (days.compareTo(new BigDecimal(10)) <= 0) {
			return value.multiply(new BigDecimal("0.005").multiply(days)).setScale(0, RoundingMode.HALF_EVEN);
		}

		return value.multiply(new BigDecimal("0.01").multiply(days)).setScale(0, RoundingMode.HALF_EVEN);
	}

}
