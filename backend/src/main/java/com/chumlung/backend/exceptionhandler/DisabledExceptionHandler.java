package com.chumlung.backend.exceptionhandler;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DisabledExceptionHandler extends Throwable{
    static final long serialVersionUID = -3387516993124229949L;

	@ExceptionHandler(DisabledException.class)
	 public ResponseEntity<String> DisabledExceptionHandler() {
	  JSONObject response = new JSONObject();
	  response.put("message", "Unauthorized");
	  return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);
	 }


}
