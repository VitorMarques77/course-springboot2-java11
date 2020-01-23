package com.example.coursespring.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.coursespring.entities.Order;
import com.example.coursespring.entities.User;
import com.example.coursespring.repositories.OrderRepository;
import com.example.coursespring.repositories.UserRepository;

@Configuration
@Profile("test")//igual o user do application.properties
public class TestConfig implements CommandLineRunner /*executar algum código antes de iniciar um aplicativo*/ {

	@Autowired //faz a injeção de depêdencia automaticamente
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		//salvando dados no db h2
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
	}
}