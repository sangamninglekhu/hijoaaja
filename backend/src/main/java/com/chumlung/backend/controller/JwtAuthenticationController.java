package com.chumlung.backend.controller;

import java.util.Objects;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chumlung.backend.config.JwtTokenUtil;
import com.chumlung.backend.exceptionhandler.DisabledExceptionHandler;
import com.chumlung.backend.model.JwtRequest;
import com.chumlung.backend.model.JwtResponse;

@RestController
@CrossOrigin(origins = "http://www.hijoaaja.com", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@SuppressWarnings("unchecked")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception, DisabledExceptionHandler {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<String> DisabledExceptionHandler(DisabledException e) {
		JSONObject response = new JSONObject();
		response.put("message", e.getMessage());
		return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> BadCredentialsExceptionHandler(BadCredentialsException e) {
		JSONObject response = new JSONObject();
		response.put("message", e.getMessage());
		return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);
	}

}
