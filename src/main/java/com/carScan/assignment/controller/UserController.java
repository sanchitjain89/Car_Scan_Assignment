package com.carScan.assignment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carScan.assignment.models.User;
import com.carScan.assignment.models.UserResponse;
import com.carScan.assignment.service.UserServiceImpl;

@RestController()
@RequestMapping("/car_scan")
public class UserController {
	
	@Autowired
	private UserServiceImpl service;

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/add_user")
	public ResponseEntity<UserResponse> addUser(@RequestBody User user) {
		logger.info("AddUser Request = " + user);
		return new ResponseEntity<>(service.saveUser(user), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> findAllUsers(){
		logger.info("findAllUsers Request");
		return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserResponse> findUserById(@PathVariable int id) {
		logger.info("findUserById Request = " + id);
		return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
	}

	@PutMapping("/user")
	public ResponseEntity<UserResponse> updateUser(@RequestBody User user){
		logger.info("updateUser Request " + user);
		return new ResponseEntity<>(service.updateUser(user), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
		logger.info("deleteProduct Request " + id);
		service.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
