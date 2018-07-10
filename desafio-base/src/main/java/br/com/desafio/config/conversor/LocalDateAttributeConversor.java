package br.com.desafio.config.conversor;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConversor implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate a) {
		return a != null ? Date.valueOf(a) : null;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date a) {
		return a != null ? a.toLocalDate() : null;
	}
}
