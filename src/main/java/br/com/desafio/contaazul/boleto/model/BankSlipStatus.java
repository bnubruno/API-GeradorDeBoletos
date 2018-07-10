package br.com.desafio.contaazul.boleto.model;

public enum BankSlipStatus {

	PENDING(0, "Pending"), //
	PAID(1, "Paid"), //
	CANCELED(2, "Canceled");

	private Integer id;
	private String description;

	private BankSlipStatus(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
