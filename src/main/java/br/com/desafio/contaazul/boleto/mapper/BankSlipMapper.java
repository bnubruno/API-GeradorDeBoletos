package br.com.desafio.contaazul.boleto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.desafio.contaazul.boleto.dto.BankSlipDTO;
import br.com.desafio.contaazul.boleto.model.BankSlip;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class })
public interface BankSlipMapper extends EntityMapper<BankSlipDTO, BankSlip> {

	default BankSlip fromId(String id) {
		if (id == null) {
			return null;
		}
		BankSlip bankSlip = new BankSlip();
		bankSlip.setId(id);
		return bankSlip;
	}

	@Mappings({ //
			@Mapping(source = "date", target = "due_date"), //
			@Mapping(source = "totalInCents", target = "total_in_cents"), //
	})
	@Override
	public BankSlipDTO toDto(BankSlip entity);

	@Mappings({ //
			@Mapping(source = "due_date", target = "date"), //
			@Mapping(source = "total_in_cents", target = "totalInCents"), //
	})
	@Override
	public BankSlip toEntity(BankSlipDTO dto);

}
