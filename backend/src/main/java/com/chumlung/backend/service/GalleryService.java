package com.chumlung.backend.service;

import java.util.List;
import java.util.Optional;

import com.chumlung.backend.model.Gallery;

public interface GalleryService {
	List<Gallery> showAll();
	
	void save(Gallery gallery);
	
	void deleteById(Long id);
	
	Optional<Gallery> findById(Long id);
	
}
