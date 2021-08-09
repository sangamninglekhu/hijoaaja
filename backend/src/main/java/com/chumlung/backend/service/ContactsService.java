package com.chumlung.backend.service;

import java.util.List;
import java.util.Optional;

import com.chumlung.backend.model.Contacts;

public interface ContactsService {
	Optional<Contacts> findById(Long id);
	
	List<Contacts> showAll();
	
	void save(Contacts contact);
	
	void delete(Contacts contact);
	

}
