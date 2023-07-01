package com.nitesh.springboot.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitesh.springboot.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByName(String name);
	
}
