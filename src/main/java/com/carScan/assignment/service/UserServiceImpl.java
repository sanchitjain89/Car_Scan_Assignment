package com.carScan.assignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carScan.assignment.exception.CarScanErrorObject;
import com.carScan.assignment.models.User;
import com.carScan.assignment.models.UserResponse;
import com.carScan.assignment.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserRepository repository;

	public UserResponse saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);
		return mapUserToUserResponse(user);
	}

	public List<UserResponse> getUsers(){
		List<User> user = repository.findAll();
		
		List<UserResponse> usersResponse = new ArrayList<>();
		
		for (User u : user) {
			usersResponse.add(new UserResponse(u.getId(), u.getFirstName(), u.getLastName(), u.getCity(), u.getMobileNumber(), u.getDate()));
		}
		
		return usersResponse;

	}

	public UserResponse getUserById(long id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()){
			return mapUserToUserResponse(user.get());
		}
		throw new CarScanErrorObject(HttpStatus.NOT_FOUND, "User not found");
	}

	public List<User> getUserByName(String name) {
		return repository.findByfirstName(name);
	}

	public void deleteUser(long id) {
		repository.deleteById(id);
	}

	public UserResponse updateUser(User user) {
		User existingUser = repository.findBymobileNumber(user.getMobileNumber()).orElse(null);
		if (existingUser == null)
			throw new CarScanErrorObject(HttpStatus.BAD_REQUEST, "No such user exist");

		if (user.getFirstName() != null)
			existingUser.setFirstName(user.getFirstName());

		if (user.getLastName() != null)
			existingUser.setLastName(user.getLastName());

		if (user.getCity() != null)
			existingUser.setCity(user.getCity());

		if (user.getDate() != null)
			existingUser.setDate(user.getDate());

		repository.save(existingUser);
		return mapUserToUserResponse(existingUser);
	}

	//this method is required for JWT token
	@Override
	public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
		User user = repository.findBymobileNumber(mobileNumber)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + mobileNumber));

		return UserDetailsImpl.build(user);
	}

	private UserResponse mapUserToUserResponse(User user){
		UserResponse response = new UserResponse();

		response.setId(user.getId());
		if (user.getFirstName() != null) response.setFirstName(user.getFirstName());
		if (user.getLastName() != null) response.setLastName(user.getLastName());
		if (user.getDate() != null) response.setDate(user.getDate());
		if (user.getCity() != null) response.setCity(user.getCity());
		response.setMobileNumber(user.getMobileNumber());

		return response;
	}

}
