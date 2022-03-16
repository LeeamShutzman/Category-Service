package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;


@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	public Category add(Category category) {
		return categoryRepository.save(category);
	}
}
