package com.chumlung.backend.service;

import java.util.List;
import java.util.Optional;

import com.chumlung.backend.model.Videolinks;

public interface VideolinksService {

	List<Videolinks> findAll();
	
	void save(Videolinks videolink);
	
	Optional<Videolinks> findById(Long id);
	
	void deleteById(Long id);
}
