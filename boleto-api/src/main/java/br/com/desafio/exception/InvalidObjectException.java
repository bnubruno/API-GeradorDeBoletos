package br.com.desafio.exception;

import br.com.desafio.config.exception.APIException;

public class InvalidObjectException extends APIException {

	private static final long serialVersionUID = 1L;

	public InvalidObjectException(String message) {
		super(message);
	}


}
