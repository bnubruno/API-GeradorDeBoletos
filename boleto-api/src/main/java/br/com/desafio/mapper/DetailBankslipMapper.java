package br.com.desafio.mapper;

import java.time.LocalDate;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

import br.com.desafio.dto.BankSlipDetailDTO;
import br.com.desafio.entity.BankSlip;
import br.com.desafio.exception.InvalidObjectException;
import br.com.desafio.mapper.EntityMapper;
import br.com.desafio.util.Util;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class })
public interface DetailBankslipMapper extends EntityMapper<BankSlipDetailDTO, BankSlip> {

	default LocalDate toDueDate(String dueDate) throws InvalidObjectException {
		if (StringUtils.isEmpty(dueDate)) {
			throw new InvalidObjectException("Bankslip not provided in the request body");
		}
		return Util.toLocalDate(dueDate).orElseThrow(() -> new InvalidObjectException("Bankslip not provided in the request body"));
	}

	@AfterMapping
	default void calculateFine(BankSlip bankSlip, @MappingTarget BankSlipDetailDTO dto) {
		dto.setFine(bankSlip.getFine());
	}

	@Mappings({ //
			@Mapping(source = "dueDate", target = "due_date"), //
			@Mapping(source = "totalInCents", target = "total_in_cents"), //
			@Mapping(source = "paymentDate", target = "payment_date"), //
	})
	public BankSlipDetailDTO toDto(BankSlip entity) throws InvalidObjectException;

	@Mappings({ //
			@Mapping(source = "total_in_cents", target = "totalInCents"), //
			@Mapping(source = "due_date", target = "dueDate"), //
			@Mapping(source = "payment_date", target = "paymentDate"), //
	})
	public BankSlip toEntity(BankSlipDetailDTO dto) throws InvalidObjectException;

}
