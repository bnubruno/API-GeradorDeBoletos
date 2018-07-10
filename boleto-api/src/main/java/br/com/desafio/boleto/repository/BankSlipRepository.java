package br.com.desafio.boleto.repository;

import org.springframework.stereotype.Repository;

import br.com.desafio.boleto.model.BankSlip;

@Repository
public interface BankSlipRepository extends AbstractCrudRespository<BankSlip, String> {

}
