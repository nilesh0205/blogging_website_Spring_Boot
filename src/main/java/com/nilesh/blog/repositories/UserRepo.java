package com.nilesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nilesh.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
