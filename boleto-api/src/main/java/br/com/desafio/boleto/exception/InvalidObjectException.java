package br.com.desafio.boleto.exception;

public class InvalidObjectException extends APIException {

	private static final long serialVersionUID = 1L;

	public InvalidObjectException(String message) {
		super(message);
	}


}
