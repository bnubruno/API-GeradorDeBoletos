package br.com.desafio.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

import br.com.desafio.dto.BankSlipDTO;
import br.com.desafio.entity.BankSlip;
import br.com.desafio.exception.InvalidObjectException;
import br.com.desafio.mapper.EntityMapper;
import br.com.desafio.util.Util;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class })
public interface BankSlipMapper extends EntityMapper<BankSlipDTO, BankSlip> {

	default LocalDate toDueDate(String dueDate) throws InvalidObjectException {
		if (StringUtils.isEmpty(dueDate)) {
			throw new InvalidObjectException("Bankslip not provided in the request body");
		}
		return Util.toLocalDate(dueDate).orElseThrow(() -> new InvalidObjectException("Bankslip not provided in the request body"));
	}

	@Mappings({ //
			@Mapping(source = "dueDate", target = "due_date"), //
			@Mapping(source = "totalInCents", target = "total_in_cents"), //
	})
	public BankSlipDTO toDto(BankSlip entity) throws InvalidObjectException;

	@Mappings({ //
			@Mapping(source = "total_in_cents", target = "totalInCents"), //
			@Mapping(source = "due_date", target = "dueDate"), //
			@Mapping(target = "paymentDate", ignore = true)
	})
	public BankSlip toEntity(BankSlipDTO dto) throws InvalidObjectException;

}
