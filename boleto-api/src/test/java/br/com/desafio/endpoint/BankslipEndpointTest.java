package br.com.desafio.endpoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.desafio.dto.CreateBankSlipDTO;
import br.com.desafio.entity.BankSlip;
import br.com.desafio.entity.BankSlipStatus;
import br.com.desafio.entity.Customer;
import br.com.desafio.mapper.CreateBankSlipMapper;
import br.com.desafio.rest.param.CreateBankSlipParam;
import br.com.desafio.service.BankSlipService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BankslipEndpointTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private BankSlipService service;

	@Autowired
	private CreateBankSlipMapper createMapper;

	@Test
	@Transactional
	public void givenBankslips_whenGetAndHaveTwoBankSlips_thenReturnTwoBankSlips() throws Exception {
		this.service.save(getOne());
		this.service.save(getTwo());

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
	@Transactional
	public void givenBankslips_whenHaveNoBankslips_thenReturnZeroBankSlips() throws Exception {
		mvc.perform(get("/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	@Transactional
	public void givenBankSlip_whengetBankslipAndExits_thenReturnBankSlipDetail() throws Exception {
		this.service.save(getOther());

		ResultActions perform = mvc.perform(get("/bankslips/" + "c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk());

		perform.andExpect(jsonPath("$.id", is("c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89")));
		perform.andExpect(jsonPath("$.due_date", is("2018-05-10")));
		perform.andExpect(jsonPath("$.payment_date", is("2018-05-13")));
		perform.andExpect(jsonPath("$.total_in_cents", is(99000)));
		perform.andExpect(jsonPath("$.customer", is("Zaphod Company")));
		perform.andExpect(jsonPath("$.status", is("PAID")));
		perform.andExpect(jsonPath("$.fine", is(1485)));
	}

	@Test
	@Transactional
	public void givenBankSlip_whenGetBankslipAndNotExits_thenReturnStatus404() {
		try {
			mvc.perform(get("/bankslips/" + "c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isNotFound());
		} catch (Exception e) {
			assertThat(e.getCause()).hasMessage("Bankslip not found with the specified id");
		}
	}

	@Test
	@Transactional
	public void givenBankSlip_whenCancelBankSlipAndStatusIsPending_thenPay() throws Exception {
		BankSlip thisOne = getOne();
		this.service.save(thisOne);

		mvc.perform(post("/bankslips/" + thisOne.getId() + "/payments?payment_date=2018-01-02").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isNoContent());
	}

	@Test
	@Transactional
	public void givenCancelBankSlip_whenIdNotExists_thenReturnNotFound() throws Exception {
		try {
			mvc.perform(delete("/bankslips/" + "THISIDNOTEXISTS").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isNotFound());
		} catch (Exception e) {
			assertThat(e.getCause()).hasMessage("Bankslip not found with the specified id");
		}
	}

	@Test
	@Transactional
	public void givenCancelBankSlip_whenStatusIsPending_thenCancelBankslip() throws Exception {
		BankSlip thisOne = getOther();
		this.service.save(thisOne);

		mvc.perform(delete("/bankslips/" + thisOne.getId()).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isNoContent());
	}

	@Test
	@Transactional
	public void givenCancelBankSlip_whenIdNotExists_thenCancelBankslip() {
		try {
			mvc.perform(delete("/bankslips/" + "NOTEXISTISID").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isNotFound());
		} catch (Exception e) {
			assertThat(e.getCause()).hasMessage("Bankslip not found with the specified id");
		}
	}

	@Test
	@Transactional
	public void givenCreateBankSlip_whenAllSRight_thenReturnOK() throws IOException, Exception {
		long countBefore = this.service.count();

		CreateBankSlipParam param = new CreateBankSlipParam();
		param.setTotalInCents(new BigDecimal(100000));
		param.setCustomer(new Customer("Ford Prefect Company"));
		param.setDueDate(LocalDate.of(2018, 1, 1));

		CreateBankSlipDTO paramDTO = this.createMapper.toDto(param);

		ResultActions perform = mvc.perform(post("/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(convertObjectToJsonBytes(paramDTO))).andExpect(status().isCreated());
		perform.andExpect(jsonPath("$.customer", is("Ford Prefect Company")));
		perform.andExpect(jsonPath("$.due_date", is("2018-01-01")));
		perform.andExpect(jsonPath("$.total_in_cents", is(100000)));

		assertThat(countBefore).isLessThan(this.service.count());
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

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		JavaTimeModule module = new JavaTimeModule();
		mapper.registerModule(module);

		return mapper.writeValueAsBytes(object);
	}

}
