package com.chumlung.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chumlung.backend.model.Role;
import com.chumlung.backend.repository.RoleRepository;
import com.chumlung.backend.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Role findByUsername(String username) {
		return roleRepository.findByUsername(username);
	}

//	@Override
//	public Optional<Role> findByEmail(String email) {
//		return roleRepository.findByEmail(email);
//	}

}
