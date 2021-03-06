package com.passersbyte.naturally.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.passersbyte.naturally.model.Product;
import com.passersbyte.naturally.model.User;
import com.passersbyte.naturally.repository.ProductRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path="/app/products") // This means URL's start with /welcome (after Application path)
public class ProductController {
	
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private ProductRepository productRepository;
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewProduct (@RequestParam String name
	 , @RequestParam String code, @RequestParam String description) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
	
		Product n = new Product();
		n.setProductName(name);
		n.setPrice(0f);
		
		n.setDescription(description);
		n.setProductCode(code);
		n.setReleaseDate(null);
		n.setStarRating(3.0f);
		n.setImageUrl(null);
		
		productRepository.save(n);
		return "Saved";
		}
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping(path="/addComplete") // Map ONLY POST Requests
	public @ResponseBody ResponseEntity<String> addNewProduct (@RequestBody Product newProduct) {
	
		Product n = new Product();
		n.setProductName(newProduct.getProductName());
		n.setPrice(0f);
		
		n.setDescription(newProduct.getDescription());
		n.setProductCode(newProduct.getProductCode());
		n.setReleaseDate(null);
		n.setStarRating(newProduct.getStarRating());
		n.setImageUrl(null);
		
		productRepository.save(n);
		return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		@CrossOrigin("http://localhost:4200")
		@GetMapping(path="/all")
		public @ResponseBody Iterable<Product> getAllProducts() {
		// This returns a JSON or XML with the users
		return productRepository.findAll();
		}
		
		@GetMapping("/topValue")
		public String greetings(Model model) {
			/*Pageable sortedByPriceDescNameAsc = 
					  PageRequest.of(0, 2, Sort.by("price").descending().and(Sort.by("productName")));
			model.addAttribute("products", sortedByPriceDescNameAsc.getContent());*/
			
			Pageable paging = PageRequest.of(0, 2, Sort.by("price").ascending()); 
			 
			Page<Product> pagedResult = productRepository.findAll(paging);
		
			/*Pageable firstPageWithTwoElements = PageRequest.of(1, 2);
			 
			Page<Product> pagedResult = productRepository.findAll(firstPageWithTwoElements);*/
			
			model.addAttribute("products", pagedResult.getContent());
			

			return "products";
		}
		
		@GetMapping("/productlist")
		public String products(Model model) {
			model.addAttribute("products", productRepository.findAll());
			return "products";
		}
		
		@GetMapping("/formnewproduct")
		public String formnewproduct(Model model) {
			return "formnewproduct";
		}
		
		@CrossOrigin("http://localhost:4200")
		@GetMapping("/{id}")
		public ResponseEntity<Product> getTutorialById(@PathVariable("id") Integer id) {
		    Optional<Product> productData = productRepository.findById(id);

		    if (productData.isPresent()) {
		      return new ResponseEntity<>(productData.get(), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		    
		 }
		
		 @CrossOrigin("http://localhost:4200")
		 @PutMapping("/{id}")
		 public ResponseEntity<Product> replaceProduct(@RequestBody Product newProduct, @PathVariable Integer id) {
		    
		    return productRepository.findById(id)
		      .map(product -> {
		    	  product.setProductName(newProduct.getProductName());
		    	  product.setPrice(newProduct.getPrice());
		  		
		    	  product.setDescription(newProduct.getDescription());
		    	  product.setProductCode(newProduct.getProductCode());
		    	  product.setReleaseDate(newProduct.getReleaseDate());
		    	  product.setStarRating(newProduct.getStarRating());
		    	  product.setImageUrl(newProduct.getImageUrl());
		  		
		          productRepository.save(product);
		          return new ResponseEntity<>(product, HttpStatus.OK);
		      })
		      .orElseGet(() -> {
		    	  newProduct.setId(id);
		         productRepository.save(newProduct);
		        return new ResponseEntity<>(newProduct, HttpStatus.OK);
		      });
		  }

		 @CrossOrigin("http://localhost:4200") 
		 @DeleteMapping("/{id}")
		 public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
			  productRepository.deleteById(id);
			  return new ResponseEntity<>(null, HttpStatus.OK);
		  }



}
