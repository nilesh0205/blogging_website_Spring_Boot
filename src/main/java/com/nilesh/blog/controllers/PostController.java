package com.nilesh.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nilesh.blog.config.AppConstants;
import com.nilesh.blog.entities.Post;
import com.nilesh.blog.payloads.ApiResponse;
import com.nilesh.blog.payloads.PostDto;
import com.nilesh.blog.payloads.PostResponse;
import com.nilesh.blog.services.FileService;
import com.nilesh.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService ;
	
	@Value("${project.image}")
	private String path;
	
	

	
	//create
	@PostMapping("user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto>createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			){
		PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
	return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/category/{cotegoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(
			@PathVariable Integer cotegoryId
			){
		List<PostDto>posts=this.postService.getPostByCategory(cotegoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER ,required = false)Integer pageNumber,
			@RequestParam(value="pageSize" , defaultValue =AppConstants.PAGE_SIZE,required=false)Integer pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConstants.STORE_BY,required=false)String shortBy,
	        @RequestParam(value="sortDir", defaultValue=AppConstants.SORT_DIR,required=false)String sortDir)
	        
	{
	PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,shortBy,sortDir);
				
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getpostById(@PathVariable Integer postId){
		
		PostDto postDto=this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	
	public ApiResponse deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ApiResponse("Post is successfully deleted !!", true );
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatPost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords	)
	{
		List<PostDto> result=this.postService.srearchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	//post image upload
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image ,
			@PathVariable Integer postId)throws IOException{
		String fileName=this.fileService.uploadImage(path,image);
		PostDto postDto=this.postService.getPostById(postId);
		postDto.setImageName(fileName);
		PostDto updatePost=this.postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//method to serve file
	@GetMapping(value="/post/image/{imageName}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
    		@PathVariable("imageName") String imageName,
    		HttpServletResponse response
    		)  throws IOException{
		InputStream resource=this.fileService.getRsource(path,imageName);
		response.setContentType(MediaType.IMAGE_GIF_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(
			@PathVariable Integer userId
			){
		List<PostDto>posts=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	   
}
