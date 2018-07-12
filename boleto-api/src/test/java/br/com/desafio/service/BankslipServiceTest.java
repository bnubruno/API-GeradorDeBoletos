package br.com.desafio.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.entity.BankSlip;
import br.com.desafio.entity.BankSlipStatus;
import br.com.desafio.entity.Customer;
import br.com.desafio.exception.EmptyRequestException;
import br.com.desafio.exception.InvalidObjectException;
import br.com.desafio.rest.param.CreateBankSlipParam;
import br.com.desafio.rest.param.PayBankSlipParam;
import br.com.desafio.service.BankSlipService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankslipServiceTest {

	@Autowired
	private BankSlipService bankSlipService;

	@Test
	@Transactional
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
	@Transactional
	public void givenParam_wheParamIsOk_thenPayBankSlip() throws InvalidObjectException, EmptyRequestException {
		BankSlip saved = createAndAssert();

		PayBankSlipParam param = new PayBankSlipParam(saved.getId(), LocalDate.of(2018, 1, 1));
		BankSlip paid = this.bankSlipService.pay(param);

		assertWithStatus(saved, paid.getId(), saved.getCustomer(), saved.getTotalInCents(), saved.getDueDate(), BankSlipStatus.PAID);
	}

	@Test
	@Transactional
	public void givenParam_whereIdNotExists_givenNotFoundError() throws InvalidObjectException {
		PayBankSlipParam param = new PayBankSlipParam("NOTEXISTSID", LocalDate.of(2018, 1, 1));
		try {
			this.bankSlipService.pay(param);
			assertFalse("Exception expected", true);
		} catch (NoResultException | EmptyRequestException e) {
			assertThat(e).hasMessage("Bankslip not found with the specified id");
		}
	}

	@Test
	@Transactional
	public void givenCancelParam_whenBankslipCanBeCanceled_thenCancelBankslip() throws InvalidObjectException, EmptyRequestException {
		BankSlip saved = createAndAssert();

		BankSlip canceled = this.bankSlipService.cancel(saved.getId());
		assertWithStatus(canceled, saved.getId(), saved.getCustomer(), saved.getTotalInCents(), saved.getDueDate(), BankSlipStatus.CANCELED);
	}

	@Test
	@Transactional
	public void givenCancelParam_whenIdNotFound_thenNotFoundError() {
		try {
			this.bankSlipService.cancel("NOTFOUNDID");
			assertFalse(true);
		} catch (NoResultException e) {
			assertThat(e).hasMessage("Bankslip not found with the specified id");
		}
	}

	private void assertWithStatus(BankSlip saved, String id, Customer customer, BigDecimal totalInCents, LocalDate dueDate, BankSlipStatus paid) {
		assertThat(id.length()).isEqualTo(36);
		assertThat(saved.getCustomer()).isEqualTo(customer);
		assertThat(saved.getTotalInCents()).isEqualTo(totalInCents);
		assertThat(saved.getDueDate()).isEqualTo(dueDate);
		assertThat(saved.getStatus()).isEqualTo(paid);
	}

}
