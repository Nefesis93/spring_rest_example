package com.corso.spring.rest.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corso.spring.rest.model.User;
import com.corso.spring.rest.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired UserService userService;
		
	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<?> getPageableUsers(Pageable pageable) {
		return ResponseEntity.ok(userService.getAllPageableUsers(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(userService.getUserById(id));
		} catch(EntityNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.insertUser(user));
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.updateUser(user));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeUser(@PathVariable long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok("User deleted");
	}
	
}
