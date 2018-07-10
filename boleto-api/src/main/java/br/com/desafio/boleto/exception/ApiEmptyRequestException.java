package br.com.desafio.boleto.exception;

public abstract class ApiEmptyRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public ApiEmptyRequestException(String message) {
		super(message);
	}
}