package br.com.desafio.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;

import org.springframework.hateoas.ResourceSupport;

import br.com.desafio.config.exception.APIException;
import br.com.desafio.dto.BankSlipDTO;
import br.com.desafio.exception.EmptyRequestException;
import br.com.desafio.rest.endpoint.BankSlipEndpoint;
import br.com.desafio.util.Util;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class BankSlipResource extends ResourceSupport {

	private final BankSlipDTO bankslip;

	public BankSlipResource(BankSlipDTO dto) {
		this.bankslip = dto;
		try {
			add(linkTo(BankSlipEndpoint.class).withRel("bankslip"));
			add(linkTo(methodOn(BankSlipEndpoint.class).detail(dto.getId())).withSelfRel());
			add(linkTo(methodOn(BankSlipEndpoint.class).pay(dto.getId(), Util.toString(LocalDate.now()))).withRel("payment"));
			add(linkTo(methodOn(BankSlipEndpoint.class).delete(dto.getId())).withRel("cancel"));
		} catch (APIException | EmptyRequestException e) {
			e.printStackTrace();
		}
	}

}
