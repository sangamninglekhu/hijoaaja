package com.chumlung.backend.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(String name, String email, String subject, String body) throws MailException {
		System.out.println("sangam!");
		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo("sangamlimbu52@gmail.com");
			mail.setSubject(subject);
			String finalbody = "From: " + name + "\n" + email + "\n \n" + body;
			mail.setText(finalbody);
			javaMailSender.send(mail);
		} catch (MailParseException e) {
			throw new MailParseException("Please remove any spaces from the name field and try again.");
		}

	}

	public void sendemail(String name, String phone, String from, String subject, String message) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("sangamlimbu52@gmail.com");
		mail.setFrom(from);
		String finalbody = "Sender: " + name + "\nPhone : " + phone + "\nFrom: " + from + "\n\n" + message;
		mail.setSubject(subject);
		mail.setText(finalbody);
		javaMailSender.send(mail);
	}

}