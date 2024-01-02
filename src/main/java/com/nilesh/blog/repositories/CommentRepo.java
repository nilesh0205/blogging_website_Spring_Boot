package com.nilesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nilesh.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
