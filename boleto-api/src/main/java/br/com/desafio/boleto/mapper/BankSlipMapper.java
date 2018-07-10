package br.com.desafio.boleto.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

import br.com.desafio.boleto.dto.BankSlipDTO;
import br.com.desafio.boleto.entity.BankSlip;
import br.com.desafio.boleto.exception.InvalidObjectException;
import br.com.desafio.mapper.EntityMapper;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class })
public interface BankSlipMapper extends EntityMapper<BankSlipDTO, BankSlip> {

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
	public BankSlipDTO toDto(BankSlip entity) throws InvalidObjectException;

	@Mappings({ //
			@Mapping(source = "total_in_cents", target = "totalInCents"), //
			@Mapping(source = "due_date", target = "dueDate"), //
	})
	public BankSlip toEntity(BankSlipDTO dto) throws InvalidObjectException;

}
