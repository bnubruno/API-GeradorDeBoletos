package br.com.desafio.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.desafio.exception.InvalidObjectException;
import br.com.desafio.mapper.BankSlipMapper;
import br.com.desafio.mapper.CreateBankSlipMapper;
import br.com.desafio.mapper.DetailBankSlipMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTests {

	@Autowired
	public BankSlipMapper bankSlipMapper;

	@Autowired
	public CreateBankSlipMapper createBankSlipMapper;

	@Autowired
	public DetailBankSlipMapper detailBankslipMapper;

	@Test
	public void givenStringDateToBankSlipMapper_whenCorretDate_thenLocalDate() throws InvalidObjectException {
		assertThat(this.bankSlipMapper.toDueDate("2018-01-01")).isEqualTo(LocalDate.of(2018, 1, 1));
	}

	@Test
	public void givenStringDateToBankSlipMapper_whenNotCorretDate_thenLocalDate() {
		try {
			this.bankSlipMapper.toDueDate("2018-01-ab");
			assertFalse(true);
		} catch (InvalidObjectException e) {
			assertThat(e).hasMessage("Bankslip not provided in the request body");
		}
	}

	@Test
	public void givenStringDateToCreateBankSlipMapper_whenCorretDate_thenLocalDate() throws InvalidObjectException {
		assertThat(this.createBankSlipMapper.toDueDate("2018-01-01")).isEqualTo(LocalDate.of(2018, 1, 1));
	}

	@Test
	public void givenStringDateToCreateBankSlipMapper_whenNotCorretDate_thenLocalDate() {
		try {
			this.createBankSlipMapper.toDueDate("2018-01-ab");
			assertFalse(true);
		} catch (InvalidObjectException e) {
			assertThat(e).hasMessage("Bankslip not provided in the request body");
		}
	}

	@Test
	public void givenStringDateToDetailBankSlipMapper_whenCorretDate_thenLocalDate() throws InvalidObjectException {
		assertThat(this.detailBankslipMapper.toDueDate("2018-01-01")).isEqualTo(LocalDate.of(2018, 1, 1));
	}

	@Test
	public void givenStringDateToDetailBankSlipMapper_whenNotCorretDate_thenLocalDate() {
		try {
			this.detailBankslipMapper.toDueDate("2018-01-ab");
			assertFalse(true);
		} catch (InvalidObjectException e) {
			assertThat(e).hasMessage("Bankslip not provided in the request body");
		}
	}

}
