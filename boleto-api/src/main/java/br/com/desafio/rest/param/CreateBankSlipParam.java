package br.com.desafio.rest.param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

import br.com.desafio.entity.Customer;
import br.com.desafio.exception.InvalidObjectException;
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
		if (Stream.of(dueDate, customer, totalInCents).anyMatch(Objects::isNull) || StringUtils.isEmpty(customer.getName())) {
			throw new InvalidObjectException("Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values");
		}
	}

}
