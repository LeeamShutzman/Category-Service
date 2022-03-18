package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;


@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	/***************************************************************/
	//Constructors, Getters, and Setters

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

	/***************************************************************/
	//Repository Method Calls

	//Add a Category
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	//View all Categories
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}

	//View Category by ID
	public Optional<Category> findByCategoryID(long categoryId){
		return categoryRepository.findById(categoryId);
	}

	//Delete a Category
	public void deleteCategory(long categoryId){
		categoryRepository.deleteById(categoryId);;
	}

}
