package com.corso.spring.rest.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.corso.spring.rest.model.User;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Slf4j
public class UserControllerTest {
	
	private static final String url = "http://localhost:8080/api/v1/users/";
	
	@Autowired TestRestTemplate restTemplate;
	@Autowired UserService userService;
	
	@Test
	public void getUserByIdController() {
		User test = userService.createFakeUser();
		
		ResponseEntity<User> re = restTemplate.getForEntity(url + test.getId(), User.class);
		User test2 = re.getBody();
		assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(test2).isEqualTo(test);
		
		
	}
}
