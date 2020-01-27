package com.example.coursespring.services.exceptions;

//classe criada para tratarmos o erro de busca de um id inexistente

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id "+id);
	}

}
