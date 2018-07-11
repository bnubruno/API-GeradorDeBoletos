package br.com.desafio.boleto;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import br.com.desafio.boleto.entity.BankSlip;
import br.com.desafio.boleto.entity.BankSlipStatus;
import br.com.desafio.boleto.entity.Customer;
import br.com.desafio.boleto.service.BankSlipService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankslipEndpointTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BankSlipService service;

	@Test
	public void givenBankslips_whenHaveTwoBankSlips_thenReturnTwoBankSlips() throws Exception {

		BankSlip bs01 = getOne();
		BankSlip bs02 = getTwo();

		given(this.service.findAll(PageRequest.of(0, 9999))).willReturn(new PageImpl<>(Arrays.asList(bs01, bs02)));

		ResultActions perform = mvc.perform(get("/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));

		perform.andExpect(jsonPath("$[0].id", is("84e8adbf-1a14-403b-ad73-d78ae19b59bf")));
		perform.andExpect(jsonPath("$[0].due_date", is("2018-01-01")));
		perform.andExpect(jsonPath("$[0].total_in_cents", is(100000)));
		perform.andExpect(jsonPath("$[0].customer", is("Ford Prefect Company")));
		perform.andExpect(jsonPath("$[0].status", is("PENDING")));

		perform.andExpect(jsonPath("$[1].id", is("c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89")));
		perform.andExpect(jsonPath("$[1].due_date", is("2018-02-01")));
		perform.andExpect(jsonPath("$[1].total_in_cents", is(200000)));
		perform.andExpect(jsonPath("$[1].customer", is("Zaphod Company")));
		perform.andExpect(jsonPath("$[1].status", is("PAID")));
	}

	@Test
	public void givenBankslips_whenHaveNoBankslips_thenReturnZeroBankSlips() throws Exception {
		given(this.service.findAll(PageRequest.of(0, 9999))).willReturn(new PageImpl<>(Arrays.asList()));
		mvc.perform(get("/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void givenBankSlip_whenBankslipExits_thenReturnBankSlipDetail() throws Exception {
		given(this.service.findById("c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89")).willReturn(Optional.of(getOther()));

		ResultActions perform = mvc.perform(get("/bankslips/c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk());

		perform.andExpect(jsonPath("$.id", is("c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89")));
		perform.andExpect(jsonPath("$.due_date", is("2018-05-10")));
		perform.andExpect(jsonPath("$.payment_date", is("2018-05-13")));
		perform.andExpect(jsonPath("$.total_in_cents", is(99000)));
		perform.andExpect(jsonPath("$.customer", is("Zaphod Company")));
		perform.andExpect(jsonPath("$.status", is("PAID")));
		perform.andExpect(jsonPath("$.fine", is(1485)));
	}

	@Test
	public void givenBankSlip_whenBankslipNotExits_thenReturnStatus404() {
		given(this.service.findById("c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89")).willReturn(Optional.ofNullable(null));

		try {
			mvc.perform(get("/bankslips/c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isNotFound());
		} catch (Exception e) {
			Assert.assertEquals(e.getCause().getMessage(), "Bankslip not found with the specified id");
		}

	}

	private BankSlip getOne() {
		BankSlip bs01 = new BankSlip();
		bs01.setId("84e8adbf-1a14-403b-ad73-d78ae19b59bf");
		bs01.setTotalInCents(new BigDecimal(100000));
		bs01.setCustomer(new Customer("Ford Prefect Company"));
		bs01.setDueDate(LocalDate.of(2018, 1, 1));
		bs01.setStatus(BankSlipStatus.PENDING);
		return bs01;
	}

	private BankSlip getTwo() {
		BankSlip bs02 = new BankSlip();
		bs02.setId("c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89");
		bs02.setTotalInCents(new BigDecimal(200000));
		bs02.setCustomer(new Customer("Zaphod Company"));
		bs02.setDueDate(LocalDate.of(2018, 2, 1));
		bs02.setPaymentDate(LocalDate.of(2018, 5, 13));
		bs02.setStatus(BankSlipStatus.PAID);
		return bs02;
	}

	private BankSlip getOther() {
		BankSlip bs02 = new BankSlip();
		bs02.setId("c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89");
		bs02.setTotalInCents(new BigDecimal(99000));
		bs02.setCustomer(new Customer("Zaphod Company"));
		bs02.setDueDate(LocalDate.of(2018, 5, 10));
		bs02.setPaymentDate(LocalDate.of(2018, 5, 13));
		bs02.setStatus(BankSlipStatus.PAID);
		return bs02;
	}

}
