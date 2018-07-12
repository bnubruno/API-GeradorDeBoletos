package br.com.desafio.boleto.rest.endpoint;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.desafio.boleto.dto.BankSlipDTO;
import br.com.desafio.boleto.dto.CreateBankSlipDTO;
import br.com.desafio.boleto.entity.BankSlip;
import br.com.desafio.boleto.exception.EmptyRequestException;
import br.com.desafio.boleto.exception.InvalidObjectException;
import br.com.desafio.boleto.mapper.BankSlipMapper;
import br.com.desafio.boleto.mapper.CreateBankSlipMapper;
import br.com.desafio.boleto.mapper.DetailBankslipMapper;
import br.com.desafio.boleto.resources.BankSlipResource;
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
@Getter
@Slf4j
@RequestMapping("/bankslips")
@RestController
public class BankSlipEndpoint extends AbstractEndpoint<BankSlip, BankSlipDTO, BankSlipMapper, BankSlipService, String> {

	@Autowired
	private CreateBankSlipMapper paramMapper;

	@Autowired
	private DetailBankslipMapper detailMapper;

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
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BankSlipDTO> create(@RequestBody CreateBankSlipDTO createBankSlipDTO) throws InvalidObjectException, EmptyRequestException {

		log.info("Creating a bankslip " + createBankSlipDTO.toString());
		if (createBankSlipDTO.isNull()) {
			throw new EmptyRequestException("Bankslip not provided in the request body");
		}
		BankSlip bankSlipSaved = getService().create(this.paramMapper.toEntity(createBankSlipDTO));
		log.info("Bankslip successfully saved");

		return new ResponseEntity<BankSlipDTO>(getMapper().toDto(bankSlipSaved), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Retorna uma lista de boletos (HATEOAS)")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "OK") //
	})
	@GetMapping(value = "/hateoas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Resources<BankSlipResource>> listHateoas(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "9999") Integer size) throws APIException {
		log.info("Fetch a lista of bankslips");
		Page<BankSlip> bankSlipsPage = getService().findAll(PageRequest.of(page, size));
		final List<BankSlipResource> collection = getMapper().toDto(bankSlipsPage.getContent()).stream().map(BankSlipResource::new).collect(Collectors.toList());

		return ResponseEntity.ok(toResource(collection));
	}

	@ApiOperation(value = "Retorna uma lista de boletos)")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "OK") //
	})
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BankSlipDTO>> list(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "9999") Integer size) throws APIException {
		log.info("Fetch a lista of bankslips");
		Page<BankSlip> bankSlipsPage = getService().findAll(PageRequest.of(page, size));
		return ResponseEntity.ok(getMapper().toDto(bankSlipsPage.getContent()));
	}

	private <T> Resources<T> toResource(final List<T> collection) {
		final Resources<T> resources = new Resources<>(collection);
		final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		resources.add(new Link(uriString, "self"));
		return resources;
	}

	@ApiOperation(value = "Retorna o boleto pelo ID")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Ok"), //
			@ApiResponse(code = 404, message = "Bankslip not found with the specified id"), //
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BankSlipDTO> detail(@PathVariable("id") String id) throws APIException {

		log.info("Find a bankslip by id " + id);
		BankSlip bankslip = getService().findById(id).orElseThrow(() -> new NoResultException("Bankslip not found with the specified id"));
		return ResponseEntity.ok(getDetailMapper().toDto(bankslip));
	}

	@ApiOperation(value = "Recebe o pagamento de um boleto")
	@ApiResponses(value = { //
			@ApiResponse(code = 204, message = "No content"), //
			@ApiResponse(code = 400, message = "Payment date not provided in the request body"), //
			@ApiResponse(code = 404, message = "Bankslip not found with the specified id"), //
	})
	@PostMapping(value = "/{id}/payments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> pay(@PathVariable("id") String idBankslip, @RequestParam(value = "payment_date") String paymentDateStr) throws InvalidObjectException, EmptyRequestException {
		LocalDate paymentDate = Util.toLocalDate(paymentDateStr).orElseThrow(() -> new EmptyRequestException("Payment date not provided in the request body"));

		log.info("Pay a bankslip by id " + idBankslip + " in " + paymentDateStr);
		getService().pay(new PayBankslipParam(idBankslip, paymentDate));

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Recebe o cancelamento de um boleto")
	@ApiResponses(value = { //
			@ApiResponse(code = 204, message = "No content"), //
			@ApiResponse(code = 404, message = "Bankslip not found with the specified id"), //
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable("id") String id) throws EmptyRequestException {

		log.info("Cancel a bankslip with id " + id);
		getService().cancel(id);

		Map<String, String> message = new HashMap<>();
		message.put("message", "Bankslip canceled");

		return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
	}

}
