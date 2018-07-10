package br.com.desafio.contaazul.boleto.exception;

public abstract class APIException extends Exception {

	private static final long serialVersionUID = 1L;

	public APIException(String message) {
		super(message);
	}
}
