package com.nilesh.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

//custom exception 
//uncheked exception
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
   String resourceName;
   String fieldName;
   long fieldValue;

   public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
	super(String.format("%s not found with this %s : %s",resourceName,fieldName,fieldValue));
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.fieldValue = fieldValue;
}
}
