package com.chumlung.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chumlung.backend.model.Category;
import com.chumlung.backend.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> showAll() {
		return categoryRepository.findAll();
	}
	
}
