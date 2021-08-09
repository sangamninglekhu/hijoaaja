//package com.chumlung.backend.controller;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.chumlung.backend.model.Login;
//import com.chumlung.backend.model.User;
//import com.chumlung.backend.service.UserService;
//
//@Controller
////@CrossOrigin(origins = "http://www.ksholdings.ltd", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
//public class LoginController {
//
//	@Autowired
//	private UserService userService;
//
//	private boolean bcrypt;
//
//	@PostMapping(value = "/user/authenticate", produces = "application/json")
//	@ResponseBody
//	public ResponseEntity<?> register(@RequestBody(required = true) Login login, HttpServletResponse response)
//			throws IOException {
//		System.out.println("wraith nth phases");
//		User loginuser = userService.findUserByRoleEmail(login.getEmail());
//
//		try {
//
//			if (loginuser != null) {
//
//				bcrypt = BCrypt.checkpw(login.getPassword(), loginuser.getPassword());
//				if (bcrypt) {
//
//					if (!loginuser.isIs_enabled()) {
//						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//								.body("Account not verified. Please verify your account in your email.");
//					} else {
//						loginuser.setToken("fake-jwt-token." + loginuser.getId());
//						return ResponseEntity.status(HttpStatus.OK).body(loginuser);
//
//					}
//
//				} else {
//					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password didn't match");
//				}
//
//			} else {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password didn't match");
//			}
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
//		}
//
//	}
//
//}