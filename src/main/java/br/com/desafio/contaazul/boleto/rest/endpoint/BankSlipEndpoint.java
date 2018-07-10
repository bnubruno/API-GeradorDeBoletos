package br.com.desafio.contaazul.boleto.rest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.contaazul.boleto.dto.BankSlipDTO;
import br.com.desafio.contaazul.boleto.dto.CreateBankSlipDTO;
import br.com.desafio.contaazul.boleto.mapper.BankSlipMapper;
import br.com.desafio.contaazul.boleto.mapper.CreateBankSlipMapper;
import br.com.desafio.contaazul.boleto.model.BankSlip;
import br.com.desafio.contaazul.boleto.service.BankSlipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Api("Bankslips")
@RequestMapping(value = "/bankslips")
@Getter
@Slf4j
@RestController
public class BankSlipEndpoint extends AbstractController<BankSlip, BankSlipDTO, BankSlipMapper, BankSlipService, String> {

	@Autowired
	private CreateBankSlipMapper createMapper;

	@Autowired
	public BankSlipEndpoint(BankSlipService service, BankSlipMapper mapper) {
		super(service, mapper);
	}

	@ApiOperation(value = "Esse método deve receber um novo boleto e inseri-lo em um banco de dados para ser consumido pela própria API. Todos os campos são obrigatórios.")
	@ApiResponses(value = { //
			@ApiResponse(code = 201, message = "Bankslip created"), //
			@ApiResponse(code = 400, message = "Bankslip not provided in the request body"), //
			@ApiResponse(code = 422, message = "Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values"), //
	})
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BankSlipDTO> create(@RequestBody CreateBankSlipDTO createBankSlipDTO) {
		log.info("Creating a bankslip " + createBankSlipDTO.toString());

		BankSlip bankSlipSaved = getService().save(this.createMapper.toEntity(createBankSlipDTO));
		log.info("Bankslip saved with sucessfully");

		return new ResponseEntity<BankSlipDTO>(getMapper().toDto(bankSlipSaved), HttpStatus.CREATED);
	}

}
