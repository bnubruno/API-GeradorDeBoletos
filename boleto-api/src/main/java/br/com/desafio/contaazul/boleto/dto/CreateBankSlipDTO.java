package br.com.desafio.contaazul.boleto.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateBankSlipDTO {

	@ApiModelProperty(value = "Data de vencimento", example = "2018-01-01", required = true)
	private String due_date;

	@ApiModelProperty(value = "Valor em centavos", example = "100000", required = true)
	private BigDecimal total_in_cents;

	@ApiModelProperty(value = "Cliente", example = "Trillian Company", required = true)
	private String customer;

	public boolean isNull() {
		return Stream.of(due_date, total_in_cents, customer).allMatch(Objects::isNull);
	}

}
