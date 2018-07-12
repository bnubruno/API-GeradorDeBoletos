package br.com.desafio.param;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import br.com.desafio.exception.EmptyRequestException;
import br.com.desafio.rest.param.PayBankSlipParam;

public class PayBankslipParamTest {

	@Test
	public void givenPayParam_whenParamIsOk_thenReturnOK() throws EmptyRequestException {
		PayBankSlipParam param = new PayBankSlipParam("123456", LocalDate.of(2018, 1, 1));

		param.validate();

		assertTrue(true);
	}

	@Test
	public void givenPayParam_whenPayParamHasNoId_thenReturnIdNotFound() {
		try {
			PayBankSlipParam param = new PayBankSlipParam(null, LocalDate.of(2018, 1, 1));
			param.validate();
			assertFalse(true);
		} catch (EmptyRequestException e) {
			assertThat(e).hasMessage("Bankslip not found with the specified id");
		}
	}

}
