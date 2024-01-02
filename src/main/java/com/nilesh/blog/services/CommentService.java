package com.nilesh.blog.services;

import org.springframework.stereotype.Service;

import com.nilesh.blog.payloads.CommentDto;


public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);
     void deleteComment(Integer commentId);
    
    
    
}
