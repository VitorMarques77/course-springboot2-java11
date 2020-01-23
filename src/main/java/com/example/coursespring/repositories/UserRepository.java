package com.example.coursespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coursespring.entities.User;

public interface UserRepository extends JpaRepository<User, Long> { // Interface JPA que já possui os metodos implementados pelo Spring
	

}
