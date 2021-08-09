package com.chumlung.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chumlung.backend.model.Videolinks;
import com.chumlung.backend.repository.VideolinksRepository;

@Service
public class VideolinksServiceImpl implements VideolinksService{

	@Autowired
	private VideolinksRepository videolinksRepository;
	
	@Override
	public List<Videolinks> findAll(){
		return videolinksRepository.findAll();
	}
	
	@Override
	public void save(Videolinks videolink) {
		videolinksRepository.save(videolink);
	}
	
	@Override
	public Optional<Videolinks> findById(Long id){
		return videolinksRepository.findById(id);
	}
	
	@Override
	public void deleteById(Long id) {
		videolinksRepository.deleteById(id);
	}
}
