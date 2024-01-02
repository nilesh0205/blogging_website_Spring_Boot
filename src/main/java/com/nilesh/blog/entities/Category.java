package com.nilesh.blog.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name="title",length = 100, nullable = false)
	private String categoryTitle;
	
	
	@Column(name="description",length = 100, nullable = false)
	private String categoryDescription;
	
	//if we remove parents so child will auto,automatically delete with the help of Cascade
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post>posts=new ArrayList<>();
}
