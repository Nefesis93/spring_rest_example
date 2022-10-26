package com.corso.spring.rest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.corso.spring.rest.model.User;

@Repository
public interface UserDAO extends PagingAndSortingRepository<User, Long> {
	
	public User findByEmail(String email);
	public boolean existsByEmail(String email);
	
}
