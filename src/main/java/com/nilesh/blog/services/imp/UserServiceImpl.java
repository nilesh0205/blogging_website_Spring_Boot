package com.nilesh.blog.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.mapping.AccessOptions.GetOptions.GetNulls;
import org.springframework.stereotype.Service;
import org.springframework.web.service.annotation.GetExchange;

import com.nilesh.blog.exceptions.*;

import com.nilesh.blog.entities.User;
import com.nilesh.blog.payloads.UserDto;
import com.nilesh.blog.repositories.UserRepo;
import com.nilesh.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
	private UserRepo userRepo; 
    
    @Autowired
    private ModelMapper modelMapper;
	
    @Override
	public boolean createUser(UserDto userDto) {
       String email = userDto.getEmail();
		UserDto isExist = getAllUsers().stream()
		.filter(user -> email.toLowerCase().equals(user.getEmail().toLowerCase()))
		.findFirst()
		.orElse(null);
		if(isExist!=null) {
		return  false;
		}
		this.userRepo.save(this.dtoToUser(userDto));
		return true;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow((()-> new ResourceNotFoundException("User","Id",userId)));
		
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser=this.userRepo.save(user);
		UserDto userDto1=this.userToUserDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto,User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	public UserDto userToUserDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
		
	}
 
}
