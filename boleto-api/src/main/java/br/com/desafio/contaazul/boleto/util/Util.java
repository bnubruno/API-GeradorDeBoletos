package br.com.desafio.contaazul.boleto.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Util {

	public static <T> List<T> toList(Iterable<T> it) {
		List<T> list = new ArrayList<>();
		it.forEach(list::add);
		return list;
	}

	public static LocalDate toLocalDate(String str) {
		try {
			return LocalDate.parse(str);
		} catch (Exception e) {
			// Like a ninja ;)
			return null;
		}
	}

}
