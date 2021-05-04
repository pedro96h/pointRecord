package com.example.demo.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.entities.Register;
import com.example.demo.entities.User;
import com.example.demo.repositories.RegisterRepository;
import com.example.demo.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RegisterRepository registerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null,"Pedro Oliveira","118-300-644-60");
		User u2 = new User(null,"Maria Fernanda","102-981-784-78");
		User u3 = new User(null,"Isabela Renata","231.912.438-10");
		
		userRepository.saveAll(Arrays.asList(u1,u2,u3));
		
		Register r1 = new Register(1L, u1, LocalDate.now(), LocalTime.now(), null, null, null);
		Register r2 = new Register(2L, u2, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), null, null);
		
		registerRepository.saveAll(Arrays.asList(r1,r2));
	}
}
