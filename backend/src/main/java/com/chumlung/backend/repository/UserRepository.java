package com.chumlung.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chumlung.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
		
	@Query( "select u from User u where u.username =:username")
	User findByUsername(String username);
	
}
