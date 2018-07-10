package br.com.desafio.boleto.model;

public interface AbstractEntity<ID> {

	public ID getId();
	
	public void setId(ID id);

}
