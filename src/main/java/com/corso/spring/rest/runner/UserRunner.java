package com.corso.spring.rest.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.corso.spring.rest.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserRunner implements ApplicationRunner{

	@Autowired UserService userService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("User Runner...");
		
		for(int i = 0; i < 10; i++)
			userService.insertUser(userService.createFakeUser());
		
		userService.insertUser(userService.createCustomUser("Mario", "Rossi", "Roma", 32, "m.rossi@example.com"));
	}

}
