package com.chumlung.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chumlung.backend.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query( "select u.userrole from User u where u.username = :username")
	Role findByUsername(@Param("username") String username);

}
