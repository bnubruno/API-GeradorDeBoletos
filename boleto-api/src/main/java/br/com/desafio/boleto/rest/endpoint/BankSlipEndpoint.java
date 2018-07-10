package br.com.desafio.boleto.rest.endpoint;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.boleto.dto.BankSlipDTO;
import br.com.desafio.boleto.dto.CreateBankSlipDTO;
import br.com.desafio.boleto.entity.BankSlip;
import br.com.desafio.boleto.exception.EmptyRequestException;
import br.com.desafio.boleto.exception.InvalidObjectException;
import br.com.desafio.boleto.mapper.BankSlipMapper;
import br.com.desafio.boleto.mapper.BankSlipParamMapper;
import br.com.desafio.boleto.mapper.BankslipDetailMapper;
import br.com.desafio.boleto.rest.param.PayBankslipParam;
import br.com.desafio.boleto.service.BankSlipService;
import br.com.desafio.config.exception.APIException;
import br.com.desafio.endpoint.AbstractEndpoint;
import br.com.desafio.util.Util;
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
public class BankSlipEndpoint extends AbstractEndpoint<BankSlip, BankSlipDTO, BankSlipMapper, BankSlipService, String> {

	@Autowired
	private BankSlipParamMapper paramMapper;

	@Autowired
	private BankslipDetailMapper detailMapper;

	@Autowired
	public BankSlipEndpoint(BankSlipService service, BankSlipMapper mapper) {
		super(service, mapper);
	}

	@ApiOperation(value = "Recebe um boleto")
	@ApiResponses(value = { //
			@ApiResponse(code = 201, message = "Bankslip created"), //
			@ApiResponse(code = 400, message = "Bankslip not provided in the request body"), //
			@ApiResponse(code = 422, message = "Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values"), //
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BankSlipDTO> create(@RequestBody CreateBankSlipDTO createBankSlipDTO) throws InvalidObjectException, EmptyRequestException {

		log.info("Creating a bankslip " + createBankSlipDTO.toString());
		if (createBankSlipDTO.isNull()) {
			throw new EmptyRequestException("Bankslip not provided in the request body");
		}
		BankSlip bankSlipSaved = getService().save(this.paramMapper.toEntity(createBankSlipDTO));
		log.info("Bankslip successfully saved");

		return new ResponseEntity<BankSlipDTO>(getMapper().toDto(bankSlipSaved), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Retorna uma lista de boletos")
	@ApiResponses(value = { //
			@ApiResponse(code = 201, message = "Bankslip created"), //
			@ApiResponse(code = 400, message = "Bankslip not provided in the request body"), //
			@ApiResponse(code = 422, message = "Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values"), //
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BankSlipDTO>> list(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "9999") Integer size) throws APIException {

		log.info("Fetch a lista of bankslips");
		PageRequest pageable = PageRequest.of(page, size);
		Page<BankSlip> bankSlipsPage = getService().findAll(pageable);

		return ResponseEntity.ok(getMapper().toDto(bankSlipsPage.getContent()));
	}

	@ApiOperation(value = "Retorna o boleto pelo ID")
	@ApiResponses(value = { //
			@ApiResponse(code = 404, message = "Bankslip not found with the specified id"), //
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BankSlipDTO> detail(@PathVariable("id") String id) throws APIException {

		log.info("Find a bankslip by id " + id);
		BankSlip bankslip = getService().findById(id).orElseThrow(() -> new NoResultException("Bankslip not found with the specified id"));
		return ResponseEntity.ok(getDetailMapper().toDto(bankslip));
	}

	@ApiResponses(value = { //
			@ApiResponse(code = 204, message = "No content"), //
			@ApiResponse(code = 404, message = "Bankslip not found with the specified id"), //
	})
	@PostMapping(value = "/{id}/payments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> create(@PathVariable("id") String idBankslip, @RequestParam(value = "payment_date") String paymentDateStr) throws InvalidObjectException, EmptyRequestException {
		LocalDate paymentDate = Util.toLocalDate(paymentDateStr);

		log.info("Pay a bankslip by id " + idBankslip + " in " + paymentDateStr);
		getService().pay(new PayBankslipParam(idBankslip, paymentDate));

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) throws EmptyRequestException {

		log.info("Cancel a bankslip with id " + id);
		getService().cancel(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
