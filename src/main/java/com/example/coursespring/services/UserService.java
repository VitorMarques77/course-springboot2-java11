package com.example.coursespring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.coursespring.entities.User;
import com.example.coursespring.repositories.UserRepository;
import com.example.coursespring.services.exceptions.DatabaseException;
import com.example.coursespring.services.exceptions.ResourceNotFoundException;

@Service //anotation para que seja possivel registrar a classe para injeção de dependencia automatica.
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		// lançamos a exception personalidada atraves do orElseThrow e instanciamos ela atraves de uma expressao lambda
		return obj.orElseThrow(()->new ResourceNotFoundException(id));
	}
	public User insert(User obj) {
		return repository.save(obj);
	}
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	public User updtade(Long id, User obj) {
		User entity = repository.getOne(id);
		updateData(entity,obj);
		return repository.save(entity);
	}
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
}
