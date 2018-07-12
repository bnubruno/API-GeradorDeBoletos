package br.com.desafio.rest.param;

import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

import br.com.desafio.exception.EmptyRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayBankslipParam {

	private String idBankslip;
	private LocalDate paymentDate;

	public void validate() {
		if (Stream.of(idBankslip, paymentDate).anyMatch(Objects::isNull)) {
			throw new EmptyRequestException("Bankslip not found with the specified id");
		}
	}

}
