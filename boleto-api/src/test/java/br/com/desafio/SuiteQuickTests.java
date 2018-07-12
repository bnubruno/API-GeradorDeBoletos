package br.com.desafio;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.desafio.dto.CreateBankSlipDTOTest;
import br.com.desafio.endpoint.BankSlipEndpointTest;
import br.com.desafio.entity.FineTest;
import br.com.desafio.mapper.MapperTests;
import br.com.desafio.param.CreateBankSlipParamTest;
import br.com.desafio.param.PayBankslipParamTest;
import br.com.desafio.service.BankSlipServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ CreateBankSlipDTOTest.class, 
		FineTest.class, 
		CreateBankSlipParamTest.class, 
		PayBankslipParamTest.class, 
		BankSlipServiceTest.class, 
		BankSlipEndpointTest.class, 
		MapperTests.class 
})
public class SuiteQuickTests {

}
