package com.ybcompany.demo.services;

import com.ybcompany.demo.dto.SignUpDto;
import com.ybcompany.demo.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<UserDto> getAllUsers(Integer page, Integer size, String sort);

    List<UserDto> getAllUsers();

    UserDto getUser(Long userId);

    UserDto addUser(SignUpDto userData);
}
