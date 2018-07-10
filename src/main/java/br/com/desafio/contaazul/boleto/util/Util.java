package br.com.desafio.contaazul.boleto.util;

import java.util.ArrayList;
import java.util.List;

public class Util {

	public static <T> List<T> toList(Iterable<T> it) {
		List<T> list = new ArrayList<>();
		it.forEach(list::add);
		return list;
	}

}
