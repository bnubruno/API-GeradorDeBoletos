package br.com.desafio.boleto.repository;

import org.springframework.stereotype.Repository;

import br.com.desafio.boleto.entity.BankSlip;
import br.com.desafio.repository.AbstractCrudRespository;

@Repository
public interface BankSlipRepository extends AbstractCrudRespository<BankSlip, String> {

}
