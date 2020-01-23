package com.example.coursespring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.coursespring.entities.User;
import com.example.coursespring.repositories.UserRepository;

@Configuration
@Profile("test")//igual o user do application.properties
public class TestConfig implements CommandLineRunner /*executar algum código antes de iniciar um aplicativo*/ {

	@Autowired //faz a injeção de depêdencia automaticamente
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		//salvando dados no db h2
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		userRepository.saveAll(Arrays.asList(u1,u2));
	}
}
