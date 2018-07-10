package br.com.desafio.contaazul.boleto.rest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.desafio.contaazul.boleto.mapper.EntityMapper;
import br.com.desafio.contaazul.boleto.model.AbstractEntity;
import br.com.desafio.contaazul.boleto.service.AbstractService;
import lombok.Getter;

@Getter
public abstract class AbstractController<T extends AbstractEntity<ID>, DTO, M extends EntityMapper<DTO, T>, S extends AbstractService<T, ID>, ID> {

	@Autowired
	private S service;

	@Autowired
	private M mapper;

	public AbstractController(S service, M mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}

}
