package com.example.coursespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coursespring.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { // Interface JPA que já possui os metodos implementados pelo Spring
	

}
