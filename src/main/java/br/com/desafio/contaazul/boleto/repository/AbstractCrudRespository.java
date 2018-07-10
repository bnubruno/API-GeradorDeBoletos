package br.com.desafio.contaazul.boleto.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractCrudRespository<T, ID extends Serializable> extends CrudRepository<T, ID> {

}
