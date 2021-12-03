package com.passersbyte.naturally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.passersbyte.naturally.model.User;
import com.passersbyte.naturally.repository.UserRepository;
import com.passersbyte.naturally.service.DBUtilityService;

@Controller // This means that this class is a Controller
@RequestMapping(path="/app/users") // This means URL's start with /welcome (after Application path)
public class UserController {
	
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String name
	 , @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
	
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		n.setPassword("");
		userRepository.save(n);
		return "Saved";
	}
		
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
	// This returns a JSON or XML with the users
	return userRepository.findAll();
	}
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/greetings")
	public String greetings(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "greetings";
	}

}