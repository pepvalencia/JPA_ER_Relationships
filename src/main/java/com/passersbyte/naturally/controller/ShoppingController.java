package com.passersbyte.naturally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.passersbyte.naturally.model.Cart;
import com.passersbyte.naturally.model.Item;
import com.passersbyte.naturally.repository.ShoppingRepository;
import com.passersbyte.naturally.service.ShoppingService;

@Controller // This means that this class is a Controller
@RequestMapping(path="/app/shopping") // This means URL's start with /welcome (after Application path)
public class ShoppingController {
	
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private ShoppingRepository shoppingRepository;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Cart> getAllShoppingCarts() {
	// This returns a JSON or XML with the users
		//ShoppingService s = new ShoppingService();
		//s.setShoppingRepository(shoppingRepository);
		//s.createCartWithItem();
		//s.addCartItem();
		//s.removeCartItem();
			
	return shoppingRepository.findAll();
	}
	
	//buyProuduct -> inserts Item in cart for user
   
}
