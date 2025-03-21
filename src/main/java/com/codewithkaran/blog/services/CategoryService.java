package com.codewithkaran.blog.services;

import java.util.List;

import com.codewithkaran.blog.payloads.CategoryDto;

public interface CategoryService {
	
    //create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//getAll
	List<CategoryDto> getCategories();
	
	//delete
	void deleteCategory(Integer categoryId);
}
