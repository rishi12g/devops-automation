package com.codewithkaran.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithkaran.blog.config.AppConstants;
import com.codewithkaran.blog.payloads.ApiResponse;
import com.codewithkaran.blog.payloads.PostDto;
import com.codewithkaran.blog.payloads.PostResponse;
import com.codewithkaran.blog.services.FileService;
import com.codewithkaran.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//POST - create posts
			@PostMapping("/user/{userId}/category/{categoryId}/posts")
			public ResponseEntity<PostDto> createCategory(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
				PostDto createPostDto = this.postService.createPost(postDto, userId, categoryId);
				return new ResponseEntity<PostDto>(createPostDto, HttpStatus.CREATED);
			}

	//Get by user
			@GetMapping("/user/{userId}/posts")
			public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
				List<PostDto> posts = this.postService.getPostsByUser(userId);
				return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
			}
			
	//Get by category
			@GetMapping("/category/{categoryId}/posts")
			public ResponseEntity<List<PostDto>> getPostsBycategory(@PathVariable Integer categoryId){
				List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
				return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
			}		
			
			//GET - get all posts
			@GetMapping("/posts")
			public ResponseEntity<PostResponse> getAllPosts(
					@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER, required=false) Integer pageNumber,
					@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
					@RequestParam(value="sortBy", defaultValue=AppConstants.SORT_BY, required=false) String sortBy,
					@RequestParam(value="sortDir", defaultValue=AppConstants.SORT_DIR, required=false) String sortDir){
				return ResponseEntity.ok(this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir));
			}
			
			// GET - get single post
			@GetMapping("/posts/{postId}")
			public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId) {
				return ResponseEntity.ok(this.postService.getPostById(postId));
			}
			
			//DELETE - delete post
			@DeleteMapping("/posts/{postId}")
			public ResponseEntity<ApiResponse> deleteUser(@PathVariable("postId") Integer pId){
				this.postService.deletePost(pId);
				return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
				//OR ResponseEntity.ok(Map.of("message","User Deleted Successfully")); 
				// OR return new ResponseEntity<>(Map.of("message","User deleted successfully", HttpStatus.OK);
			}
			
			//PUT - update post
			@PutMapping("/posts/{postId}")    // {userId} - path URI variable iski value get k liye @PathVariable use Karege 
			public ResponseEntity<PostDto> updatePosts(@Valid @RequestBody PostDto postDto,@PathVariable Integer postId){
				//if in @PathVariable Integer userId  needs to change userId to uid then write @PathVariable("userId") Integer uid
				PostDto updatedPost = this.postService.updatePost(postDto,postId);
				return ResponseEntity.ok(updatedPost);
			}
			
			//search posts
			@GetMapping("/posts/search/{keywords}")
			public ResponseEntity<List<PostDto>> searchPostbytitle(@PathVariable String keywords ){
				List<PostDto> searchPosts= this.postService.searchPosts(keywords);
				return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);
			}
			
			//post image upload
			@PostMapping("/post/image/upload/{postId}")
			public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
					@PathVariable Integer postId) throws IOException {
				
				PostDto postDto = this.postService.getPostById(postId);
				String fileName = this.fileService.uploadImage(path, image);
				postDto.setImageName(fileName);
				PostDto updatePost = this.postService.updatePost(postDto, postId);
				return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
			}
			
			//method to serve files
			
			@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
			public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
				InputStream resource = this.fileService.getResource(path, imageName);
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
				StreamUtils.copy(resource, response.getOutputStream());
			}
}
