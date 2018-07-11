package br.com.desafio.boleto.dto;

import org.junit.Assert;
import org.junit.Test;

public class CreateBankSlipDTOTest {

	@Test
	public void givenDTO_whenDtoIsNull_thenTrue() {
		CreateBankSlipDTO dto = new CreateBankSlipDTO();
		Assert.assertEquals(true, dto.isNull());
	}
	
	@Test
	public void givenDTO_whenDtoIsNotNull_thenFalse() {
		CreateBankSlipDTO dto = new CreateBankSlipDTO();
		dto.setDue_date("2018-01-01");
		Assert.assertEquals(false, dto.isNull());
	}

}
