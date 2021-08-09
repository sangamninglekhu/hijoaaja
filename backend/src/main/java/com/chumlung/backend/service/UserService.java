package com.chumlung.backend.service;

import java.util.List;

import com.chumlung.backend.model.User;

public interface UserService {
	List<User> showAll();
	
	User findByUsername(String username);

}
