package br.com.desafio.contaazul.boleto.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateBankSlipDTO {

	private String due_date;

	private BigDecimal total_in_cents;

	private String customer;

}
