package br.com.desafio.boleto.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.desafio.boleto.BankslipServiceTest;
import br.com.desafio.boleto.dto.CreateBankSlipDTOTest;
import br.com.desafio.boleto.entity.FineTest;
import br.com.desafio.boleto.param.CreateBankSlipParamTest;
import br.com.desafio.boleto.param.PayBankslipParamTest;

@RunWith(Suite.class)
@SuiteClasses({ CreateBankSlipDTOTest.class, //
		FineTest.class, //
		CreateBankSlipParamTest.class, //
		PayBankslipParamTest.class, //
		BankslipServiceTest.class //
})
public class SuiteQuickTests {

}
