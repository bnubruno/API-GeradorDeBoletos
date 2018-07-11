package br.com.desafio.boleto.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data()
@EqualsAndHashCode(callSuper = true)
public class BankSlipDetailDTO extends BankSlipDTO {

	private BigDecimal fine;

	private String payment_date;

}
