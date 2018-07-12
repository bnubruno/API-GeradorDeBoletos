package br.com.desafio.repository;

import org.springframework.stereotype.Repository;

import br.com.desafio.entity.BankSlip;
import br.com.desafio.repository.AbstractCrudRespository;

@Repository
public interface BankSlipRepository extends AbstractCrudRespository<BankSlip, String> {

}
