package com.corso.spring.rest.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.corso.spring.rest.model.User;
import com.corso.spring.rest.repository.UserDAO;

@Service
public class UserService {

	@Autowired UserDAO userDAO;
	
	@Autowired @Qualifier("fakeUserBean") ObjectProvider<User> fakeUserProvider;
	@Autowired @Qualifier("customUserBean") ObjectProvider<User> customUserProvider;
	
	private static final String EXISTS = "user email already exists";
	private static final String NOT_EXISTS = "user not exists";
	
	public User createFakeUser() {
		return fakeUserProvider.getObject();
	}
	
	public User createCustomUser(String name, String lastName, String city,
			int age, String email) {
		return User.builder()
				.name(name)
				.lastName(lastName)
				.city(city)
				.age(age)
				.email(email)
				.build();
	}
	
	public User insertUser(User user) {
		if(userDAO.existsByEmail(user.getEmail())) {
			throw new EntityExistsException(EXISTS); 
		}
		return userDAO.save(user);
	}
	
	public User updateUser(User user) {
		if(!userDAO.existsById(user.getId())) {
			throw new EntityNotFoundException(NOT_EXISTS);
		}
		return userDAO.save(user);
	}
	
	public void deleteUser(long id) {
		if(!userDAO.existsById(id)) {
			throw new EntityNotFoundException(NOT_EXISTS);
		}
		userDAO.deleteById(id);
	}
	
	public User getUserById(long id) {
		if(!userDAO.existsById(id)) {
			throw new EntityNotFoundException(NOT_EXISTS);
		}
		return userDAO.findById(id).get();
	}
	
	public List<User> getAllUsers() {
		return (List<User>) userDAO.findAll();
	}
	
	public Page<User> getAllPageableUsers(Pageable pageable) {
		return userDAO.findAll(pageable);
	}
	
}
