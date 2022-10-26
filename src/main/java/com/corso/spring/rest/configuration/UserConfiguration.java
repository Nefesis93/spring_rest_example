package com.corso.spring.rest.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.corso.spring.rest.model.User;
import com.github.javafaker.Faker;

@Configuration
public class UserConfiguration {

	@Bean("customUserBean")
	@Scope("prototype")
	public User customUser() {
		return new User();
	}
	
	@Bean("fakeUserBean")
	@Scope("prototype")
	public User fakeUser() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		String name = fake.name().firstName();
		String lastname = fake.name().lastName();
		return User.builder()
				.name(name)
				.lastName(lastname)
				.city(fake.address().cityName())
				.age(fake.number().numberBetween(18, 60))
				.email(generateFakeMail(name, lastname))
				.build();
	}
	
	private String generateFakeMail(String name, String lastname) {
		return name.replace(" ", "").substring(0, 1).toLowerCase() + "." + lastname.replace(" ", "").toLowerCase() + "@example.com";
	}
}
