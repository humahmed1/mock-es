package com.example.es;

import com.example.es.dao.RetailCustomerRepository;
import com.example.es.model.RetailCustomer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RetailCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailCustomerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RetailCustomerRepository retailCustomerRepository){
		return args -> {
			List<RetailCustomer> customers = new ArrayList<>(Arrays.asList(
            new RetailCustomer(1L, "Humaira", false),
            new RetailCustomer(2L, "Pankaj", false),
            new RetailCustomer(3L, "John", true)
    		));
			customers.forEach(customer -> retailCustomerRepository.save(new RetailCustomer(customer)));
		};
	}

}
