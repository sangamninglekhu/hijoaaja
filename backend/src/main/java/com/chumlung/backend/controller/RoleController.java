package com.chumlung.backend.controller;

import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chumlung.backend.model.Role;
import com.chumlung.backend.model.Username;
import com.chumlung.backend.service.RoleService;

@Controller
@CrossOrigin(origins = "http://www.hijoaaja.com", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class RoleController {

	@Autowired
	private RoleService roleService;

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/role/getrole")
	public ResponseEntity<?> getRole(@RequestBody Username username){
		try {
			System.out.println("Role!!!");
			Role roleId = roleService.findByUsername(username.getUsername());
			return ResponseEntity.ok(roleId.getId());
		} catch (Exception e) {
			JSONObject response = new JSONObject();
			response.put("message", e.getMessage());
			return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);
		}


		
	}


}
