package br.com.desafio.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankSlipDTO {

	@ApiModelProperty(value = "Identificador do boleto", example = "84e8adbf-1a14-403b-ad73-d78ae19b59bf", required = true)
	private String id;

	@ApiModelProperty(value = "Data de vencimento", example = "2018-01-01", required = true)
	private String due_date;

	@ApiModelProperty(value = "Valor em centavos", example = "100000", required = true)
	private BigDecimal total_in_cents;

	@ApiModelProperty(value = "Cliente", example = "Trillian Company", required = true)
	private String customer;

	@ApiModelProperty(value = "Situação", example = "PENDING", allowableValues = "PENDING, PAID, CANCELED", required = true)
	private String status;

}
