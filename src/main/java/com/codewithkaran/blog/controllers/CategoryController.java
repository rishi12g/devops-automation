package com.codewithkaran.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithkaran.blog.payloads.ApiResponse;
import com.codewithkaran.blog.payloads.CategoryDto;
import com.codewithkaran.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//POST - create category
		@PostMapping("/")
		public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
			CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
			return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
		}
		
		//PUT - update user
		@PutMapping("/{categoryId}")    // {userId} - path URI variable iski value get k liye @PathVariable use Karege 
		public ResponseEntity<CategoryDto> updateUser(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
			//if in @PathVariable Integer userId  needs to change userId to uid then write @PathVariable("userId") Integer uid
			CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,categoryId);
			return ResponseEntity.ok(updatedCategory);
		}
		
		//DELETE - delete user
		@DeleteMapping("/{categoryId}")
		public ResponseEntity<ApiResponse> deleteUser(@PathVariable("categoryId") Integer catid){
			this.categoryService.deleteCategory(catid);
			return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
			//OR ResponseEntity.ok(Map.of("message","User Deleted Successfully")); 
			// OR return new ResponseEntity<>(Map.of("message","User deleted successfully", HttpStatus.OK);
		}
		
		//GET - get all categories
		@GetMapping("/")
		public ResponseEntity<List<CategoryDto>> getAllCategories(){
			return ResponseEntity.ok(this.categoryService.getCategories());
		}
		
		// GET - get single category
		@GetMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId) {
			return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
		}
}
