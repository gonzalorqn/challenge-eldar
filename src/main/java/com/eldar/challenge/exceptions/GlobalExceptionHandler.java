package com.eldar.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<String> returnErrorIllegalArg(IllegalArgumentException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> returnErrorGenerico(Exception exception) {
		return new ResponseEntity<>("Ocurrió un error inesperado: " + exception.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<String> returnErrorNumberFormat(NumberFormatException exception) {
		return new ResponseEntity<>("Valor inesperado. El ID ingresado debe ser un número.", HttpStatus.BAD_REQUEST);
	}
}
