package br.com.desafio.param;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import br.com.desafio.entity.Customer;
import br.com.desafio.exception.InvalidObjectException;
import br.com.desafio.rest.param.CreateBankSlipParam;

public class CreateBankSlipParamTest {

	@Test
	public void givenCreateParam_whenParamIsOk_thenReturnOk() throws InvalidObjectException {

		CreateBankSlipParam param = new CreateBankSlipParam();
		param.setCustomer(new Customer("Company Customer"));
		param.setDueDate(LocalDate.of(2018, 1, 1));
		param.setTotalInCents(new BigDecimal(99000));

		param.validate();

		assertTrue(true);
	}

	@Test
	public void givenCreateParam_whenParaHasNoCustomer_thenReturnInvalidObjectError() {
		try {
			CreateBankSlipParam param = new CreateBankSlipParam();
			param.setDueDate(LocalDate.of(2018, 1, 1));
			param.setTotalInCents(new BigDecimal(99000));
			param.validate();
			assertFalse("Exception expected", true);
		} catch (InvalidObjectException e) {
			assertThat(e).hasMessage("Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values");
		}
	}

	@Test
	public void givenCreateParam_wheenParamIsNull_thenReturnInvalidObjectError() {
		CreateBankSlipParam param = new CreateBankSlipParam();
		try {
			param.validate();
			assertFalse("Exception expected", true);
		} catch (InvalidObjectException e) {
			assertTrue(true);
		}
	}

}
