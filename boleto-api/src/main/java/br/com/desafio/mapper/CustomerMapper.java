package br.com.desafio.mapper;

import org.mapstruct.Mapper;

import br.com.desafio.entity.Customer;

@Mapper(componentModel = "spring")
public class CustomerMapper {

	public String map(Customer customer) {
		return customer.getName();
	}

	public Customer map(String customer) {
		return new Customer(customer);
	}

}
