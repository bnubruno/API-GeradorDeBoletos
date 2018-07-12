package br.com.desafio.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.entity.BankSlip;
import br.com.desafio.entity.BankSlipStatus;
import br.com.desafio.exception.EmptyRequestException;
import br.com.desafio.exception.InvalidObjectException;
import br.com.desafio.repository.BankSlipRepository;
import br.com.desafio.rest.param.CreateBankSlipParam;
import br.com.desafio.rest.param.PayBankslipParam;

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
	public BankSlip create(CreateBankSlipParam createParam) throws InvalidObjectException {
		createParam.validate();

		BankSlip bankSlip = createBankSlipByParam(createParam);
		return save(bankSlip);
	}

	/**
	 * 
	 * Altera a situação para Pago
	 * 
	 * @param payParam
	 * @return
	 * @throws EmptyRequestException
	 */
	public BankSlip pay(PayBankslipParam payParam) {
		payParam.validate();

		return changeStatusTo(payParam.getIdBankslip(), BankSlipStatus.PAID);
	}

	/**
	 * Altera a situação para Cancelado
	 * 
	 * @param idBankslip
	 * @return
	 * @throws EmptyRequestException
	 */
	public BankSlip cancel(String idBankslip) throws NoResultException {

		return changeStatusTo(idBankslip, BankSlipStatus.CANCELED);
	}

	private BankSlip createBankSlipByParam(CreateBankSlipParam param) {
		BankSlip bankSlip = new BankSlip();
		bankSlip.setCustomer(param.getCustomer());
		bankSlip.setDueDate(param.getDueDate());
		bankSlip.setTotalInCents(param.getTotalInCents());

		bankSlip.setStatus(BankSlipStatus.PENDING);
		return bankSlip;
	}

	private BankSlip changeStatusTo(String idBankslip, BankSlipStatus newStatus) {
		BankSlip bankSlip = findById(idBankslip).orElseThrow(() -> new NoResultException("Bankslip not found with the specified id"));
		bankSlip.setStatus(newStatus);
		return save(bankSlip);
	}

	@Override
	public boolean isIdGeneratorService() {
		return true;
	}

}
