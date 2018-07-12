package br.com.desafio.boleto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CreateBankSlipDTOTest {

	@Test
	public void givenDTO_whenDtoIsNull_thenTrue() {
		CreateBankSlipDTO dto = new CreateBankSlipDTO();
		assertThat(dto.isNull()).isTrue();
	}

	@Test
	public void givenDTO_whenDtoIsNotNull_thenFalse() {
		CreateBankSlipDTO dto = new CreateBankSlipDTO();
		dto.setDue_date("2018-01-01");
		assertThat(dto.isNull()).isFalse();
	}

}
