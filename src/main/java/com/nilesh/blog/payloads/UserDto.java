package com.nilesh.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
   
	@NotEmpty
	@Size(min=4 , message="UserName must be minof 4 char!!")
	private String name;
	
	@NotEmpty
	@Email(message="Email address is not valid")
	private String email;
    
	@NotEmpty
	@Size(min=3 , max=10 , message="password must be min of 3 char and max of 10 char!!")
	private String password;
	
    @NotEmpty
	private String about;

}
