package com.chumlung.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chumlung.backend.model.News;
import com.chumlung.backend.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;
	
	@Override
	public void save(News news) {
		newsRepository.save(news);
	}
	
	@Override
	public List<News> showAll(){
		return newsRepository.findAll();
	}
	
	@Override
	public 	Optional<News> findById(Long id){
		return newsRepository.findById(id);
	}
	
	@Override
	public void deleteById(Long id) {
		newsRepository.deleteById(id);
	}

	@Override
	public void delete(News news) {
		newsRepository.delete(news);

	}

}
