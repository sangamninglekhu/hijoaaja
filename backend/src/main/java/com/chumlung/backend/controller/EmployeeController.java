package com.chumlung.backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chumlung.backend.model.User;
import com.chumlung.backend.service.UserService;

@RestController
@CrossOrigin(origins = "http://www.hijoaaja.com", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class EmployeeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/admin/greeting", method = RequestMethod.GET)
    public String getEmployees() {
        return "Welcome!";
    }
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUsers() {
        return userService.findByUsername("sangam");
    }
}