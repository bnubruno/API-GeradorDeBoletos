package br.com.desafio.contaazul.boleto.model;

public interface AbstractEntity<ID> {

	public ID getId();
	
	public void setId(ID id);

}
