package br.com.desafio.boleto.exception;

import br.com.desafio.config.exception.ApiEmptyRequestException;

public class EmptyRequestException extends ApiEmptyRequestException {

	private static final long serialVersionUID = 1L;

	public EmptyRequestException(String message) {
		super(message);
	}


}
