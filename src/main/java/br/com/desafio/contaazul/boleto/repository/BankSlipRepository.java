package br.com.desafio.contaazul.boleto.repository;

import org.springframework.stereotype.Repository;

import br.com.desafio.contaazul.boleto.model.BankSlip;

@Repository
public interface BankSlipRepository extends AbstractCrudRespository<BankSlip, String> {

}
