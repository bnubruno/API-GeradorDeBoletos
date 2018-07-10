package br.com.desafio.boleto.rest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.desafio.boleto.model.AbstractEntity;
import br.com.desafio.boleto.service.AbstractService;
import lombok.Getter;

@Getter
public abstract class AbstractEndpoint<T extends AbstractEntity<ID>, DTO, M, S extends AbstractService<T, ID>, ID> {

	@Autowired
	private S service;

	@Autowired
	private M mapper;

	public AbstractEndpoint(S service, M mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}

}
