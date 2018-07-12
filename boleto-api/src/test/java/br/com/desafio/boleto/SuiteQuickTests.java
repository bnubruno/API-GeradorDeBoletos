package br.com.desafio.boleto;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.desafio.boleto.dto.CreateBankSlipDTOTest;
import br.com.desafio.boleto.endpoint.BankslipEndpointTest;
import br.com.desafio.boleto.entity.FineTest;
import br.com.desafio.boleto.mapper.MapperTests;
import br.com.desafio.boleto.param.CreateBankSlipParamTest;
import br.com.desafio.boleto.param.PayBankslipParamTest;
import br.com.desafio.boleto.service.BankslipServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ CreateBankSlipDTOTest.class, //
		FineTest.class, //
		CreateBankSlipParamTest.class, //
		PayBankslipParamTest.class, //
		BankslipServiceTest.class, //
		BankslipEndpointTest.class, //
		MapperTests.class //
})
public class SuiteQuickTests {

}
