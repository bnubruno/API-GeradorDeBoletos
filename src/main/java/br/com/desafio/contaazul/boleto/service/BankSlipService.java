package br.com.desafio.contaazul.boleto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.contaazul.boleto.model.BankSlip;
import br.com.desafio.contaazul.boleto.model.BankSlipStatus;
import br.com.desafio.contaazul.boleto.repository.BankSlipRepository;
import br.com.desafio.contaazul.boleto.rest.param.CreateBankSlipParam;

@Service
public class BankSlipService extends AbstractServiceImpl<BankSlipRepository, BankSlip, String> {

	@Autowired
	public BankSlipService(BankSlipRepository repository) {
		super(repository);
	}

	public BankSlip save(CreateBankSlipParam param) {
		param.validate();

		BankSlip bankSlip = createBankSlipByParam(param);
		return save(bankSlip);
	}

	private BankSlip createBankSlipByParam(CreateBankSlipParam param) {
		BankSlip bankSlip = new BankSlip();
		bankSlip.setCustomer(param.getCustomer());
		bankSlip.setDate(param.getDate());
		bankSlip.setTotalInCents(param.getTotalInCents());

		bankSlip.setStatus(BankSlipStatus.PENDING);
		return bankSlip;
	}

	@Override
	public boolean isIdGeneratorService() {
		return true;
	}

}
