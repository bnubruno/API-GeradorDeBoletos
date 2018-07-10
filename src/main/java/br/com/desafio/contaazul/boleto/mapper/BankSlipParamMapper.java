package br.com.desafio.contaazul.boleto.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

import br.com.desafio.contaazul.boleto.dto.CreateBankSlipDTO;
import br.com.desafio.contaazul.boleto.exception.InvalidObjectException;
import br.com.desafio.contaazul.boleto.rest.param.CreateBankSlipParam;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class })
public interface BankSlipParamMapper extends EntityMapper<CreateBankSlipDTO, CreateBankSlipParam> {

	default LocalDate toDueDate(String dueDate) throws InvalidObjectException {
		if (StringUtils.isEmpty(dueDate)) {
			throw new InvalidObjectException("Bankslip not provided in the request body");
		}
		return LocalDate.parse(dueDate);
	}

	@Mappings({ //
			@Mapping(source = "dueDate", target = "due_date"), //
			@Mapping(source = "totalInCents", target = "total_in_cents"), //
	})
	public CreateBankSlipDTO toDto(CreateBankSlipParam entity) throws InvalidObjectException;

	@Mappings({ //
			@Mapping(source = "due_date", target = "dueDate"), //
			@Mapping(source = "total_in_cents", target = "totalInCents"), //
	})
	public CreateBankSlipParam toEntity(CreateBankSlipDTO dto) throws InvalidObjectException;
}
