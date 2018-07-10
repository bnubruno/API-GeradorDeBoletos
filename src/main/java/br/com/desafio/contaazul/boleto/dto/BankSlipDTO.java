package br.com.desafio.contaazul.boleto.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankSlipDTO {

	private String id;

	private String due_date;

	private BigDecimal total_in_cents;

	private String customer;

	private String status;

}
