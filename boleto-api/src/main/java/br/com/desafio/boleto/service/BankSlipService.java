package br.com.desafio.boleto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.boleto.exception.EmptyRequestException;
import br.com.desafio.boleto.exception.InvalidObjectException;
import br.com.desafio.boleto.model.BankSlip;
import br.com.desafio.boleto.model.BankSlipStatus;
import br.com.desafio.boleto.repository.BankSlipRepository;
import br.com.desafio.boleto.rest.param.CreateBankSlipParam;
import br.com.desafio.boleto.rest.param.PayBankslipParam;

@Service
public class BankSlipService extends AbstractServiceImpl<BankSlipRepository, BankSlip, String> {

	@Autowired
	public BankSlipService(BankSlipRepository repository) {
		super(repository);
	}

	/**
	 * 
	 * Cria um Boleto utilizando a data de vencimento, cliente e valor em centavos.
	 * 
	 * @param CreateBankSlipParam
	 * @return {@link BankSlip}
	 * @throws InvalidObjectException
	 */
	public BankSlip save(CreateBankSlipParam createParam) throws InvalidObjectException {
		createParam.validate();

		BankSlip bankSlip = createBankSlipByParam(createParam);
		return save(bankSlip);
	}

	public BankSlip pay(PayBankslipParam payParam) throws EmptyRequestException {
		payParam.validate();

		BankSlip bankSlip = findById(payParam.getIdBankslip()).orElseThrow(() -> new EmptyRequestException("Bankslip not found with the specified id"));
		bankSlip.setStatus(BankSlipStatus.PAID);

		return save(bankSlip);
	}

	private BankSlip createBankSlipByParam(CreateBankSlipParam param) {
		BankSlip bankSlip = new BankSlip();
		bankSlip.setCustomer(param.getCustomer());
		bankSlip.setDueDate(param.getDueDate());
		bankSlip.setTotalInCents(param.getTotalInCents());

		bankSlip.setStatus(BankSlipStatus.PENDING);
		return bankSlip;
	}

	@Override
	public boolean isIdGeneratorService() {
		return true;
	}

}
