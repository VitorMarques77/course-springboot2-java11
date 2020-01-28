package com.example.coursespring.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.coursespring.entities.Product;
import com.example.coursespring.repositories.ProductRepository;
import com.example.coursespring.services.exceptions.DatabaseException;
import com.example.coursespring.services.exceptions.ResourceNotFoundException;

@Service // anotation para que seja possivel registrar a classe para injeção de
			// dependencia automatica.
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Product insert(Product obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Product update(Long id, Product obj) {
		try {
		Product p1 = repository.getOne(id);
		updateData(obj, p1);
		return repository.save(p1);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product obj, Product p1) {
		p1.setName(obj.getName());
		p1.setPrice(obj.getPrice());
		p1.setDescription(obj.getDescription());
		p1.setImgUrl(obj.getImgUrl());
	}
}
