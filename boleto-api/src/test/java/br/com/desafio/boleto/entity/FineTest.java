package br.com.desafio.boleto.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.com.desafio.boleto.entity.Fine;

public class FineTest {

	@Test
	public void givenFineParam_whenBankSlipNaoEstaVencido_thenReturnZero() {
		BigDecimal fine = Fine.of(new BigDecimal("99000"), LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1));

		Assert.assertEquals(new BigDecimal("0").setScale(0, RoundingMode.HALF_EVEN), fine.setScale(0, RoundingMode.HALF_EVEN));
	}
	
	@Test
	public void givenParam_whenBankslipIsAdiantadoMaisDe10Dias_thenReturnZero() {
		LocalDate vencimento = LocalDate.of(2018, 1, 1);
		BigDecimal fine = Fine.of(new BigDecimal("99000"), vencimento, vencimento.minusDays(10));

		Assert.assertEquals(new BigDecimal("0").setScale(0, RoundingMode.HALF_EVEN), fine.setScale(0, RoundingMode.HALF_EVEN));
	}
	
	@Test
	public void givenParam_whenIsDue_thenFine() {
		LocalDate vencimento = LocalDate.of(2018, 1, 1);
		BigDecimal fine = Fine.of(new BigDecimal("99000"), vencimento, vencimento.plusDays(5));

		Assert.assertEquals(new BigDecimal("2475").setScale(0, RoundingMode.HALF_EVEN), fine.setScale(0, RoundingMode.HALF_EVEN));
	}
	
	@Test
	public void givenParam_whenIsDue10days_thenFine() {
		LocalDate vencimento = LocalDate.of(2018, 1, 1);
		BigDecimal fine = Fine.of(new BigDecimal("99000"), vencimento, vencimento.plusDays(10));

		Assert.assertEquals(new BigDecimal("4950").setScale(0, RoundingMode.HALF_EVEN), fine.setScale(0, RoundingMode.HALF_EVEN));
	}
	
	@Test
	public void givenParam_whenIsDue15Days_thenFine() {
		LocalDate vencimento = LocalDate.of(2018, 1, 1);
		BigDecimal fine = Fine.of(new BigDecimal("99000"), vencimento, vencimento.plusDays(15));

		Assert.assertEquals(new BigDecimal("14850").setScale(0, RoundingMode.HALF_EVEN), fine.setScale(0, RoundingMode.HALF_EVEN));
	}

}
