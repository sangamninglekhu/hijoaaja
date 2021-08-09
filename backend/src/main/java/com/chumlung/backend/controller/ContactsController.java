package com.chumlung.backend.controller;

import com.chumlung.backend.model.Contacts;
import com.chumlung.backend.model.JwtResponse;
import com.chumlung.backend.service.ContactsService;
import com.chumlung.backend.service.EmailService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@CrossOrigin(origins = "http://www.chumlung.com", maxAge = 3600)

@Controller
@SuppressWarnings("unchecked")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ContactsController {

	@Autowired
	private ContactsService contactsService;

	@Autowired
	private EmailService emailService;

	@RequestMapping("/contacts/showall")
	@ResponseBody
	public List<Contacts> showAll() {
		return contactsService.showAll();
	}
	
//	@RequestMapping("/contacts/view")
//	@ResponseBody
//	public List<Contacts> view(@RequestBody(required = true) Contacts contact) {
//		return contactsService.showAll();
//	}

	@PostMapping("/contacts/add")
	public ResponseEntity<?> add(@RequestBody(required = true) Contacts contact) {
		try {
			emailService.sendEmail(contact.getName(), contact.getEmail(), "Inquiry from website", contact.getComment());
			
			LocalDateTime now = LocalDateTime.now();
			Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
			Date date = Date.from(instant);
			contact.setDate(date);

			contactsService.save(contact);
			
			JSONObject response = new JSONObject();
			response.put("message", "Thank you for contacting to us. We will respond to you as soon as possible!");
			return ResponseEntity.status(HttpStatus.OK)
					.body(response.toString());
		} catch (MailException e) {
			JSONObject response = new JSONObject();
			response.put("message", e.getMessage());
			return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/admin/contacts/delete")
	public ResponseEntity<?> delete(@RequestBody(required = true)Contacts contact){
		try {
		Optional<Contacts> contactToDeleteCheck = contactsService.findById(contact.getId());
		if(contactToDeleteCheck.isPresent()) {
			Contacts contactToDelete = contactToDeleteCheck.get();
			contactsService.delete(contactToDelete); 
			JSONObject response = new JSONObject();
			response.put("message", "Data deleted successfully.");
			return ResponseEntity.status(HttpStatus.OK)
					.body(response.toString());

		}
		else {
			JSONObject response = new JSONObject();
			response.put("message", "Record not found");
			return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);
		}
		}catch(Exception e) {
			JSONObject response = new JSONObject();
			response.put("message", e.getMessage());
			return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);

		}
	}
}
