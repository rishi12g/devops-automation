package com.codewithkaran.blog.services;

import java.util.List;

import com.codewithkaran.blog.entities.Post;
import com.codewithkaran.blog.payloads.PostDto;
import com.codewithkaran.blog.payloads.PostResponse;

public interface PostService {

	//create 
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//Get all posts
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//Get single post
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
	
}
