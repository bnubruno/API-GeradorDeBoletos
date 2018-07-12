package br.com.desafio.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import br.com.desafio.entity.Fine;

public class FineTest {

	@Test
	public void givenFineParam_whenBankSlipNaoEstaVencido_thenReturnZero() {
		BigDecimal fine = Fine.of(new BigDecimal("99000"), LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1));
		assertThat(fine).isEqualByComparingTo(BigDecimal.ZERO);
	}

	@Test
	public void givenParam_whenBankslipIsAdiantadoMaisDe10Dias_thenReturnZero() {
		BigDecimal fine = Fine.of(new BigDecimal("99000"), LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1).minusDays(10));
		assertThat(fine).isEqualByComparingTo(BigDecimal.ZERO);
	}

	@Test
	public void givenParam_whenIsDue_thenFine() {
		LocalDate vencimento = LocalDate.of(2018, 1, 1);
		BigDecimal fine = Fine.of(new BigDecimal("99000"), vencimento, vencimento.plusDays(5));
		assertThat(fine).isEqualByComparingTo(new BigDecimal("2475"));
	}

	@Test
	public void givenParam_whenIsDue10days_thenFine() {
		LocalDate vencimento = LocalDate.of(2018, 1, 1);
		BigDecimal fine = Fine.of(new BigDecimal("99000"), vencimento, vencimento.plusDays(10));
		assertThat(fine).isEqualByComparingTo(new BigDecimal("4950"));
	}

	@Test
	public void givenParam_whenIsDue15Days_thenFine() {
		LocalDate vencimento = LocalDate.of(2018, 1, 1);
		BigDecimal fine = Fine.of(new BigDecimal("99000"), vencimento, vencimento.plusDays(15));
		assertThat(fine).isEqualByComparingTo(new BigDecimal("14850"));
	}

}
