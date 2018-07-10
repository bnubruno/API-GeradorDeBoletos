package br.com.desafio.contaazul.boleto.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.desafio.contaazul.boleto.model.AbstractEntity;
import br.com.desafio.contaazul.boleto.repository.AbstractCrudRespository;
import br.com.desafio.contaazul.boleto.util.Util;
import lombok.Getter;

@Getter
public class AbstractServiceImpl<R extends AbstractCrudRespository<T, ID>, T extends AbstractEntity<ID>, ID extends Serializable> implements AbstractService<T, ID> {

	public R repository;

	public AbstractServiceImpl(R repository) {
		super();
		this.repository = repository;
	}

	@Override
	public T save(T entity) {
		if (isIdGeneratorService()) {
			entity.setId(getNewID());
		}
		return getRepository().save(entity);
	}

	@Override
	public List<T> saveAll(Iterable<T> entities) {
		return Util.toList(getRepository().saveAll(entities));
	}

	@Override
	public boolean exists(ID id) {
		return getRepository().existsById(id);
	}

	@Override
	public List<T> findAll() {
		return Util.toList(getRepository().findAll());
	}

	@Override
	public List<T> findAll(List<ID> ids) {
		return Util.toList(getRepository().findAll());
	}

	@Override
	public long count() {
		return getRepository().count();
	}

	@Override
	public void deleteById(ID id) {
		getRepository().deleteById(id);
	}

	@Override
	public void delete(T entity) {
		getRepository().delete(entity);
	}

	@Override
	public Optional<T> findById(ID id) {
		return getRepository().findById(id);
	}

	@SuppressWarnings("unchecked")
	private ID getNewID() {
		return (ID) UUID.randomUUID().toString();
	}

	public boolean isIdGeneratorService() {
		return false;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

}
