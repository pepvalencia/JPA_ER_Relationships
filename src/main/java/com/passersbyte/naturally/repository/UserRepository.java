package com.passersbyte.naturally.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.passersbyte.naturally.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	List<User> findByName(String title);
	
	@Query("SELECT u FROM User u where u.name= :name")
	public User getUserByName(@Param("name") String name);

}
