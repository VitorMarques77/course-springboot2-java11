package com.example.coursespring.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.coursespring.services.exceptions.ResourceNotFoundException;

@ControllerAdvice // anotacao para informar que as exception devem ser direcionadas para essa classe
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) // anotacao para informar qual exception esta sendo tratada nesse metodo
	//metodo recebe de argumentos a excecao personalizada a ser tratada e um request feito pela classe resource
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		// é instanciado o standarerror com as atributos definidos acima
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		// retornado o responseentity.status para definir o código de resposta e depois retornamos o body com o corpo do erro.
		return ResponseEntity.status(status).body(err);
	}
}
