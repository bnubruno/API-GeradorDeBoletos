package br.com.desafio.boleto.param;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.com.desafio.boleto.exception.EmptyRequestException;
import br.com.desafio.boleto.rest.param.PayBankslipParam;

public class PayBankslipParamTest {

	@Test
	public void givenPayParam_whenParamIsOk_thenReturnOK() throws EmptyRequestException {
		PayBankslipParam param = new PayBankslipParam("123456", LocalDate.of(2018, 1, 1));

		param.validate();

		Assert.assertTrue(true);
	}

	@Test
	public void givenPayParam_whenPayParamHasNoId_thenReturnIdNotFound() {
		PayBankslipParam param = new PayBankslipParam(null, LocalDate.of(2018, 1, 1));
		try {
			param.validate();
		} catch (EmptyRequestException e) {
			Assert.assertEquals("Bankslip not found with the specified id", e.getMessage());
			return;
		}
		Assert.assertFalse(true);
	}

}
