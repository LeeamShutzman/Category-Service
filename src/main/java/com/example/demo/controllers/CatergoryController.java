package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping("categories")
public class CatergoryController {

	private CategoryService categoryService;

	/***************************************************************/
	//Constructors, Getters, and Setters

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

	/***************************************************************/
	//Service endpoint mapping

	//Add a Category
	@PostMapping("/add") //localhost:portNum/categories/add
	public Category addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}

	//View all Categories
	@GetMapping("/all") //localhost:portNum/categories/all
	public List<Category> getAllCategories(){
		return categoryService.findAll();
	}

	//View Category by ID
	@GetMapping("/getCategoryByID") //localhost:portNum/categories/getCategoryByID?categoryID=#
	public Optional<Category> getCategoryById(@RequestParam long categoryID){
		return categoryService.findByCategoryID(categoryID);
	}

	//Delete a Category
	@DeleteMapping("/delete") //localhost:portNum/categories/delete?categoryID=#
	public void deleteCategory(@RequestParam long categoryID){
		categoryService.deleteCategory(categoryID);
	}
	
	@PutMapping("/update") //localhost:portNum/orders/update
	public Category updateCategory(@RequestParam long categoryID, @RequestBody Category category) {
		return categoryService.updateCategory(categoryID, category);
	}
	
}
