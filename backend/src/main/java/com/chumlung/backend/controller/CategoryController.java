package com.chumlung.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chumlung.backend.model.Category;
import com.chumlung.backend.service.CategoryService;

@Controller
//@CrossOrigin(origins = "http://www.chumlung.com", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/category/showall")
	@ResponseBody
	public List<Category> showAllNews() {
		return categoryService.showAll();
	}

}
