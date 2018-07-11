package br.com.desafio.boleto.param;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.com.desafio.boleto.entity.Customer;
import br.com.desafio.boleto.exception.InvalidObjectException;
import br.com.desafio.boleto.rest.param.CreateBankSlipParam;

public class CreateBankSlipParamTest {

	@Test
	public void givenCreateParam_whenParamIsOk_thenReturnOk() throws InvalidObjectException {

		CreateBankSlipParam param = new CreateBankSlipParam();
		param.setCustomer(new Customer("Company Customer"));
		param.setDueDate(LocalDate.of(2018, 1, 1));
		param.setTotalInCents(new BigDecimal(99000));

		param.validate();

		Assert.assertTrue(true);
	}

	@Test
	public void givenCreateParam_whenParaHasNoCustomer_thenReturnInvalidObjectError() {
		CreateBankSlipParam param = new CreateBankSlipParam();
		param.setDueDate(LocalDate.of(2018, 1, 1));
		param.setTotalInCents(new BigDecimal(99000));

		try {
			param.validate();
		} catch (InvalidObjectException e) {
			Assert.assertTrue(true);
			Assert.assertEquals("Bankslip not provided in the request body", e.getMessage());
			return;
		}
		Assert.assertFalse(true);
	}

	@Test
	public void givenCreateParam_wheenParamIsNull_thenReturnInvalidObjectError() {
		CreateBankSlipParam param = new CreateBankSlipParam();
		try {
			param.validate();
		} catch (InvalidObjectException e) {
			Assert.assertTrue(true);
			return;
		}
		Assert.assertFalse(true);
	}

}
