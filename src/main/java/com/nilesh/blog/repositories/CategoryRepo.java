package com.nilesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nilesh.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
