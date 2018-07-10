/**
 *
 */
package br.com.desafio.contaazul.boleto.mapper;

import java.util.List;

import br.com.desafio.contaazul.boleto.exception.APIException;

/**
 * @author brmsouza
 *
 */
public interface EntityMapper<D, E> {

	public D toDto(E entity) throws APIException;

	public E toEntity(D dto) throws APIException;

	public List<E> toEntity(List<D> dtoList) throws APIException;

	public List<D> toDto(List<E> entityList) throws APIException;
}
