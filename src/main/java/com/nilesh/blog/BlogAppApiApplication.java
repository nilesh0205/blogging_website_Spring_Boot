package com.nilesh.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
	
	//spring container create object of ModelMaper automatically with the help of @Bean , where i want to use 
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
