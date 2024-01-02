package com.nilesh.blog.services;

import java.util.List;

import com.nilesh.blog.entities.Post;
import com.nilesh.blog.payloads.PostDto;
import com.nilesh.blog.payloads.PostResponse;

public interface PostService {
    //post
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
     
    //update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all post
  PostResponse getAllPost(Integer pagenumber,Integer pageSize,String sort, String sortDir);
  
  //get single post
  PostDto getPostById(Integer postId);
  
  //get all post by category
  List<PostDto> getPostByCategory(Integer categoryId);
  
  //get all post by user
  List<PostDto> getPostByUser(Integer userId);
  
  //search post
  List<PostDto> srearchPosts(String keyword);  
  
	
     
}
