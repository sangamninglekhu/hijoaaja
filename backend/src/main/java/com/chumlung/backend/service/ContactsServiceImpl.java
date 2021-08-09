package com.chumlung.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chumlung.backend.model.Contacts;
import com.chumlung.backend.repository.ContactsRepository;

@Service
public class ContactsServiceImpl implements ContactsService{
	
	@Override
	public Optional<Contacts> findById(Long id) {
		return contactsRepository.findById(id);
	}

	@Autowired
	private ContactsRepository contactsRepository;

	@Override
	public List<Contacts> showAll() {
		return contactsRepository.findAll();
	}

	@Override
	public void save(Contacts contact) {
		contactsRepository.save(contact);		
	}

	@Override
	public void delete(Contacts contact) {
		contactsRepository.delete(contact);
	}


}
