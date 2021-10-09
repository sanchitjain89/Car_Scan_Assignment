package com.carScan.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.carScan.assignment.models.User;
import com.carScan.assignment.service.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
	@PostMapping("/add_user")
	public User addUser(@RequestBody User user) {
		return service.saveUser(user);
	}
	
	@GetMapping("/users")
	public List<User> findAllUsers(){
		return service.getUsers();
	}
	
	@GetMapping("/user/{id}")
	public User findUserById(@PathVariable int id) {
		return service.getUserById(id);
	}

	@PutMapping("/update_user")
	public User updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteUser(id);
	}
}
