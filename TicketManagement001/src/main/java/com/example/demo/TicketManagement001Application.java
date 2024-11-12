package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketManagement001Application {

	public static void main(String[] args) {
		SpringApplication.run(TicketManagement001Application.class, args);
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
