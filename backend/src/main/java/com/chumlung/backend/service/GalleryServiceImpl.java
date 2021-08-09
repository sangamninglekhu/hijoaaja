package com.chumlung.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chumlung.backend.model.Gallery;
import com.chumlung.backend.repository.GalleryRepository;

@Service
public class GalleryServiceImpl implements GalleryService {
	@Autowired
	private GalleryRepository galleryRepository;
	
	@Override
	public List<Gallery> showAll(){
		return galleryRepository.findAll();
	}
	
	@Override
	public void save(Gallery gallery) {
		galleryRepository.save(gallery);
	}
	
	@Override
	public void deleteById(Long id) {
		galleryRepository.deleteById(id);
	}
	
	@Override
	public Optional<Gallery> findById(Long id) {
		return galleryRepository.findById(id);
	}
}
