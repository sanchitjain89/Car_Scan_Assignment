package com.carScan.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carScan.assignment.entity.User;
import com.carScan.assignment.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	public User getUserById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<User> getUserByName(String name) {
		return repository.findByfirstName(name);
	}
	
	public String deleteUser(int id) {
		repository.deleteById(id);
		return "User Deleted";
	}
	
	public User updateUser(User user) {
//		User user = repository.findById(user.getId()).orElse(null);
		return null;
		
		
	}

}
