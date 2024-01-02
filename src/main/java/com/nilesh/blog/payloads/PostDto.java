package com.nilesh.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.nilesh.blog.entities.Category;
import com.nilesh.blog.entities.Comment;
import com.nilesh.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
     
	private Integer postId;
     
     private String title;
     
     private String content;
     
     private String imageName="default.png";
     
     private Date addedDate;
     
     private CategoryDto category;
     
     private UserDto user;
     
     private Set<CommentDto> comments = new HashSet<>();
} 
