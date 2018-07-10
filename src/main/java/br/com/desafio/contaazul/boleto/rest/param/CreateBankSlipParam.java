package br.com.desafio.contaazul.boleto.rest.param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

import br.com.desafio.contaazul.boleto.exception.InvalidObjectException;
import br.com.desafio.contaazul.boleto.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBankSlipParam {

	private LocalDate dueDate;
	private BigDecimal totalInCents;
	private Customer customer;

	public void validate() throws InvalidObjectException {
		if (Stream.of(dueDate, customer, totalInCents).allMatch(Objects::isNull)) {
			throw new InvalidObjectException("Bankslip not provided in the request body");
		}
	}

}
