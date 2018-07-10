package br.com.desafio.contaazul.boleto.exception;

public class EmptyRequestException extends ApiEmptyRequestException {

	private static final long serialVersionUID = 1L;

	public EmptyRequestException(String message) {
		super(message);
	}


}
