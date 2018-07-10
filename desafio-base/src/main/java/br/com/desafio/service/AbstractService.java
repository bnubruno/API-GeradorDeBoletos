package br.com.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.desafio.entity.AbstractEntity;

public interface AbstractService<T extends AbstractEntity<ID>, ID> {

	T save(T entity);

	List<T> saveAll(Iterable<T> entities);

	boolean exists(ID id);

	List<T> findAll();

	List<T> findAll(List<ID> ids);

	long count();

	void deleteById(ID id);

	void delete(T entity);

	Optional<T> findById(ID id);
	
	Page<T> findAll(Pageable pageable);

}
