package com.carScan.assignment.service;

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
import com.carScan.assignment.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user);
	}

	public List<User> getUsers(){
		return repository.findAll();
	}

	public Optional<User> getUserById(long id) {
		return repository.findById(id);
	}

	public List<User> getUserByName(String name) {
		return repository.findByfirstName(name);
	}

	public String deleteUser(long id) {
		repository.deleteById(id);
		return "User Deleted";
	}

	public User updateUser(User user) {
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

		return repository.save(existingUser);
	}

	//this method is required for JWT token
	@Override
	public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
		User user = repository.findBymobileNumber(mobileNumber)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + mobileNumber));

		return UserDetailsImpl.build(user);
	}

}
