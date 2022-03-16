package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping("categories") //localhost:portNum/categories
public class CatergoryController {
	private CategoryService categoryService;

	public CatergoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/all") //localhost:portNum/categories/all
	public List<Category> getAllCategories(){
		return categoryService.findAll();
	}
	@PostMapping("/add") //localhost:portNum/categories/add
	public Category addCategory(@RequestBody Category category) {
		return categoryService.add(category);
	}
}
