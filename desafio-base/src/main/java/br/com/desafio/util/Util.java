package br.com.desafio.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Util {

	public static <T> List<T> toList(Iterable<T> it) {
		List<T> list = new ArrayList<>();
		it.forEach(list::add);
		return list;
	}

	public static Optional<LocalDate> toLocalDate(String str) {
		try {
			return Optional.ofNullable(LocalDate.parse(str));
		} catch (Exception e) {
			return Optional.ofNullable(null);
		}
	}

	public static String toString(LocalDate date) {
		return DateTimeFormatter.ISO_LOCAL_DATE.format(date);
	}

}
