package br.com.desafio.boleto.rest.param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

import br.com.desafio.boleto.entity.Customer;
import br.com.desafio.boleto.exception.InvalidObjectException;
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
