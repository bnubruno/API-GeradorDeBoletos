package br.com.desafio.boleto.mapper;

import java.time.LocalDate;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

import br.com.desafio.boleto.dto.BankSlipDetailDTO;
import br.com.desafio.boleto.exception.InvalidObjectException;
import br.com.desafio.boleto.model.BankSlip;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class })
public interface BankslipDetailMapper extends EntityMapper<BankSlipDetailDTO, BankSlip> {

	default LocalDate toDueDate(String dueDate) throws InvalidObjectException {
		if (StringUtils.isEmpty(dueDate)) {
			throw new InvalidObjectException("Bankslip not provided in the request body");
		}
		return LocalDate.parse(dueDate);
	}

	@AfterMapping
	default void calculateFine(BankSlip bankSlip, @MappingTarget BankSlipDetailDTO dto) {
		dto.setFine(bankSlip.getFine(LocalDate.now()).toPlainString());
	}

	@Mappings({ //
			@Mapping(source = "dueDate", target = "due_date"), //
			@Mapping(source = "totalInCents", target = "total_in_cents"), //
	})
	public BankSlipDetailDTO toDto(BankSlip entity) throws InvalidObjectException;

	@Mappings({ //
			@Mapping(source = "total_in_cents", target = "totalInCents"), //
			@Mapping(source = "due_date", target = "dueDate"), //
	})
	public BankSlip toEntity(BankSlipDetailDTO dto) throws InvalidObjectException;

}
