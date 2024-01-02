package com.nilesh.blog.services;

import java.util.List;

import com.nilesh.blog.payloads.UserDto;

public interface UserService {
    boolean createUser(UserDto user);
    UserDto updateUser(UserDto user , Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
