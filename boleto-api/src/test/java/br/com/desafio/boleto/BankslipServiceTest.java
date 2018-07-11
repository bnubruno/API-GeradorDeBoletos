package br.com.desafio.boleto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.desafio.boleto.entity.BankSlip;
import br.com.desafio.boleto.entity.BankSlipStatus;
import br.com.desafio.boleto.entity.Customer;
import br.com.desafio.boleto.exception.EmptyRequestException;
import br.com.desafio.boleto.exception.InvalidObjectException;
import br.com.desafio.boleto.rest.param.CreateBankSlipParam;
import br.com.desafio.boleto.rest.param.PayBankslipParam;
import br.com.desafio.boleto.service.BankSlipService;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = { BankSlipService.class })
@DataJpaTest
public class BankslipServiceTest {

	@Autowired
	private BankSlipService bankSlipService;

	@Test
	public void givenBankslip_whenParamIsOK_thenSaveBankSlip() throws InvalidObjectException {
		createAndAssert();
	}

	private BankSlip createAndAssert() throws InvalidObjectException {
		CreateBankSlipParam param = new CreateBankSlipParam();
		param.setCustomer(new Customer("Trillian Company"));
		param.setTotalInCents(new BigDecimal("100000"));
		param.setDueDate(LocalDate.of(2018, 1, 1));

		BankSlip saved = this.bankSlipService.create(param);

		assertWithStatus(saved, saved.getId(), saved.getCustomer(), saved.getTotalInCents(), saved.getDueDate(), BankSlipStatus.PENDING);
		return saved;
	}

	@Test
	public void givenParam_wheParamIsOk_thenPayBankSlip() throws InvalidObjectException, EmptyRequestException {
		BankSlip saved = createAndAssert();

		PayBankslipParam param = new PayBankslipParam(saved.getId(), LocalDate.of(2018, 1, 1));
		BankSlip paid = this.bankSlipService.pay(param);

		assertWithStatus(saved, paid.getId(), saved.getCustomer(), saved.getTotalInCents(), saved.getDueDate(), BankSlipStatus.PAID);
	}

	@Test
	public void givenParam_whereIdNotExists_givenNotFoundError() throws InvalidObjectException {
		PayBankslipParam param = new PayBankslipParam("NOTEXISTSID", LocalDate.of(2018, 1, 1));
		try {
			this.bankSlipService.pay(param);
		} catch (EmptyRequestException e) {
			Assert.assertEquals("Bankslip not found with the specified id", e.getMessage());
			return;
		}
		Assert.assertFalse(true);
	}

	@Test
	public void givenCancelParam_whenBankslipCanBeCanceled_thenCancelBankslip() throws InvalidObjectException, EmptyRequestException {
		BankSlip saved = createAndAssert();

		BankSlip canceled = this.bankSlipService.cancel(saved.getId());
		assertWithStatus(canceled, saved.getId(), saved.getCustomer(), saved.getTotalInCents(), saved.getDueDate(), BankSlipStatus.CANCELED);

	}

	@Test
	public void givenCancelParam_whenIdNotFound_thenNotFoundError() {
		try {
			this.bankSlipService.cancel("NOTFOUNDID");
		} catch (EmptyRequestException e) {
			Assert.assertEquals("Bankslip not found with the specified id", e.getMessage());
			return;
		}

		Assert.assertFalse(true);
	}

	private void assertWithStatus(BankSlip saved, String id, Customer customer, BigDecimal totalInCents, LocalDate dueDate, BankSlipStatus paid) {
		Assert.assertEquals(36, id.length());
		Assert.assertEquals(saved.getCustomer(), customer);
		Assert.assertEquals(saved.getTotalInCents(), totalInCents);
		Assert.assertEquals(saved.getDueDate(), dueDate);
		Assert.assertEquals(saved.getStatus(), paid);
	}

}
