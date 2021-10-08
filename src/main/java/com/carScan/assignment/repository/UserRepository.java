package com.carScan.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carScan.assignment.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findByfirstName(String name);

}
