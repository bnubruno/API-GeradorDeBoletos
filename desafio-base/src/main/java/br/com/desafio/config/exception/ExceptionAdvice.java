package br.com.desafio.config.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

	@ExceptionHandler(value = Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleAnyException(final Exception exception, final WebRequest request) {
		log.error(exception.getMessage(), exception);
		return getDefaultErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = NoResultException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleNoResultException(final Exception exception, final WebRequest request) {
		log.error(exception.getMessage(), exception);
		return getDefaultErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = APIException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleApiException(final APIException exception, final WebRequest request) {
		log.error(exception.getMessage(), exception);
		return getApiMessageException(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(value = ApiEmptyRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleEmptyException(final ApiEmptyRequestException exception, final WebRequest request) {
		log.error(exception.getMessage(), exception);
		return getApiMessageException(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Map<String, Object>> getApiMessageException(final String message, final HttpStatus httpStatus) {
		final Map<String, Object> errorMap = getMessageErrorMap(message, httpStatus);
		return new ResponseEntity<>(errorMap, httpStatus);
	}

	private Map<String, Object> getMessageErrorMap(String message, HttpStatus httpStatus) {
		final Map<String, Object> map = new LinkedHashMap<>();
		map.put("timestamp", System.currentTimeMillis());
		map.put("status", httpStatus.value());
		map.put("message", message);
		return map;
	}

	private ResponseEntity<Map<String, Object>> getDefaultErrorResponse(final String message, final HttpStatus httpStatus) {
		final Map<String, Object> errorMap = getMessageErrorMap(message, httpStatus);
		return new ResponseEntity<>(errorMap, httpStatus);
	}

}
