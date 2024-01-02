package com.nilesh.blog.services.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nilesh.blog.entities.Comment;
import com.nilesh.blog.entities.Post;
import com.nilesh.blog.exceptions.ResourceNotFoundException;
import com.nilesh.blog.payloads.CommentDto;
import com.nilesh.blog.repositories.CommentRepo;
import com.nilesh.blog.repositories.PostRepo;
import com.nilesh.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
   @Autowired
	private PostRepo postRepo;
	
   @Autowired
	private CommentRepo commentRepo;
   
   @Autowired
   private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post id", postId));
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment,CommentDto.class) ;
	}

	@Override
	public void deleteComment(Integer commentId) {
		
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId", commentId));
	    this.commentRepo.delete(comment);
	}

}
