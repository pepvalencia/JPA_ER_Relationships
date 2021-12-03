package com.passersbyte.naturally.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.passersbyte.naturally.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	List<User> findByName(String title);

}
