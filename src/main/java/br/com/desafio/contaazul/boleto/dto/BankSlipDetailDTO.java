package br.com.desafio.contaazul.boleto.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data()
@EqualsAndHashCode(callSuper = true)
public class BankSlipDetailDTO extends BankSlipDTO {

	private String fine;

}
