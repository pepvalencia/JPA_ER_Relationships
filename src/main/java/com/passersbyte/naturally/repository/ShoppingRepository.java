package com.passersbyte.naturally.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import com.passersbyte.naturally.model.Cart;
import com.passersbyte.naturally.model.Item;

public interface ShoppingRepository extends JpaRepository<Cart,Integer>{
	
	@Procedure(procedureName = "findCartsByTotal")
	List<Cart> findCartsByTotal(Float arg);
	
	@Procedure(procedureName = "findItemsByCart")
	List<Item> findItemsByCart(Integer cartId);
	
}
