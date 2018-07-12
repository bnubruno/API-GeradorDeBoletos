package br.com.desafio.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

import br.com.desafio.dto.CreateBankSlipDTO;
import br.com.desafio.exception.InvalidObjectException;
import br.com.desafio.mapper.EntityMapper;
import br.com.desafio.rest.param.CreateBankSlipParam;
import br.com.desafio.util.Util;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class })
public interface CreateBankSlipMapper extends EntityMapper<CreateBankSlipDTO, CreateBankSlipParam> {

	default LocalDate toDueDate(String dueDate) throws InvalidObjectException {
		if (StringUtils.isEmpty(dueDate)) {
			throw new InvalidObjectException("Bankslip not provided in the request body");
		}
		return Util.toLocalDate(dueDate).orElseThrow(() -> new InvalidObjectException("Bankslip not provided in the request body"));
	}

	@Mappings({ 
			@Mapping(source = "dueDate", target = "due_date"), 
			@Mapping(source = "totalInCents", target = "total_in_cents"), 
	})
	public CreateBankSlipDTO toDto(CreateBankSlipParam entity) throws InvalidObjectException;

	@Mappings({ 
			@Mapping(source = "due_date", target = "dueDate"), 
			@Mapping(source = "total_in_cents", target = "totalInCents"), 
	})
	public CreateBankSlipParam toEntity(CreateBankSlipDTO dto) throws InvalidObjectException;
}
