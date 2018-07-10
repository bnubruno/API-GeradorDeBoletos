package br.com.desafio.contaazul.boleto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.desafio.contaazul.boleto.dto.CreateBankSlipDTO;
import br.com.desafio.contaazul.boleto.rest.param.CreateBankSlipParam;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class })
public interface CreateBankSlipMapper extends EntityMapper<CreateBankSlipDTO, CreateBankSlipParam> {

	@Mappings({ //
			@Mapping(source = "date", target = "due_date"), //
			@Mapping(source = "totalInCents", target = "total_in_cents"), //
	})
	@Override
	public CreateBankSlipDTO toDto(CreateBankSlipParam entity);

	@Mappings({ //
			@Mapping(source = "due_date", target = "date"), //
			@Mapping(source = "total_in_cents", target = "totalInCents"), //
	})
	@Override
	public CreateBankSlipParam toEntity(CreateBankSlipDTO dto);

}
