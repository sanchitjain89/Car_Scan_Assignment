package com.carScan.assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carScan.assignment.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByfirstName(String name);
	
	Optional<User> findBymobileNumber(String mobile);

}
