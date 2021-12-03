package com.passersbyte.naturally.service;

import org.springframework.stereotype.Service;

import com.passersbyte.naturally.model.Cart;
import com.passersbyte.naturally.model.Item;
import com.passersbyte.naturally.repository.ShoppingRepository;

@Service
public class ShoppingService {
	
	private ShoppingRepository shoppingRepository;
	
	public ShoppingRepository getShoppingRepository() {
		return shoppingRepository;
	}

	public void setShoppingRepository(ShoppingRepository shoppingRepository) {
		this.shoppingRepository = shoppingRepository;
	}

	public void createCartWithItem() {
		
		Cart cart = new Cart();
		cart.setName("PepCart1");
		 
		Item item = new Item();
		item.setDiscount(10);
		item.setSellValue(20);
		cart.addItem(item);
		
		shoppingRepository.save(cart);
		
	}
	
	public void addCartItem() {
		
		Cart cart = shoppingRepository.getById(9);
		Item item = new Item();
		item.setDiscount(20);
		item.setSellValue(30);
		cart.addItem(item);
		
		shoppingRepository.save(cart);
		 
	}
	
	public void removeCartItem() {
		
		Cart cart = shoppingRepository.getById(9);
		Item item = cart.getItems().get(0);
		
		// here to control if at least one child restriction if desired
		cart.removeItem(item);
		
		shoppingRepository.save(cart);
		
	}
	
	public void createCartWithNoItem() {
		
		Cart cart = new Cart();
		cart.setName("PepCart0");
		 
		shoppingRepository.save(cart);
		
	}

}
