package com.chumlung.backend.service;

import java.util.List;
import java.util.Optional;

import com.chumlung.backend.model.Role;

public interface RoleService {

	List<Role> findAll();

	Optional<Role> findById(Long id);
	
	Role findByUsername(String username);

//	Optional<Role> findByEmail(String email);

}
