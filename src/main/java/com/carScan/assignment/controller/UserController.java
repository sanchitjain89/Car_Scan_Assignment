package com.carScan.assignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carScan.assignment.exception.CarScanErrorObject;
import com.carScan.assignment.models.User;
import com.carScan.assignment.service.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
	@PostMapping("/add_user")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<>(service.saveUser(user), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> findAllUsers(){
		return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> findUserById(@PathVariable int id) {
		Optional<User> user = service.getUserById(id);
		if (user.isPresent()){
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		}
		throw new CarScanErrorObject(HttpStatus.NOT_FOUND, "User not found");
	}

	@PutMapping("/update_user")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		return new ResponseEntity<>(service.updateUser(user), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteProduct(@PathVariable int id) {
		service.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
