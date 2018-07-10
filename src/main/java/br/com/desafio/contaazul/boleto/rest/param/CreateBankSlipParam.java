package br.com.desafio.contaazul.boleto.rest.param;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.desafio.contaazul.boleto.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBankSlipParam {

	private LocalDate date;
	private BigDecimal totalInCents;
	private Customer customer;

	public void validate() {

	}

}
