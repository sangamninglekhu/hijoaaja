package com.chumlung.backend.service;

import java.util.List;
import java.util.Optional;

import com.chumlung.backend.model.News;

public interface NewsService {
	
	void save(News news); 
	
	List<News> showAll();
	
	Optional<News> findById(Long id);
	
	void deleteById(Long id);
	
	void delete(News id);
}
