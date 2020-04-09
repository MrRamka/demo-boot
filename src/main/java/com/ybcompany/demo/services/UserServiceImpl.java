package com.ybcompany.demo.services;

import com.ybcompany.demo.dto.SignUpDto;
import com.ybcompany.demo.dto.UserDto;
import com.ybcompany.demo.models.User;
import com.ybcompany.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get users using pagination and sorting type
     *
     * @param page     page number
     * @param size     amount users on page
     * @param property sorting type
     * @return List of UserDto
     * @see UserDto
     */

    @Override
    public List<UserDto> getAllUsers(Integer page, Integer size, String property) {
        Sort sort = Sort.by(property);
        PageRequest request = PageRequest.of(page, size, sort);
        Page<User> pageResult = userRepository.findAll(request);
        List<User> users = pageResult.getContent();
        return UserDto.from(users);
    }

    /**
     * Get all users
     *
     * @return List of UserDto
     * @see UserDto
     */

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserDto.from(users);
    }


    /**
     * Get single user using id
     *
     * @param userId
     * @return UserDto
     * @see UserDto
     */
    @Override
    public UserDto getUser(Long userId) {
        return UserDto.from(userRepository.getOne(userId));
    }


    /**
     * Save single user from SignUpDto
     *
     * @param userData
     * @return UserDto
     * @see UserDto
     * @see SignUpDto
     */
    @Override
    public UserDto addUser(SignUpDto userData) {
        User user = User.builder()
                .username(userData.getUsername())
                .fullName(userData.getFullName())
                .createdAt(LocalDateTime.now())
                .hashPassword(userData.getPassword())
                .build();
        userRepository.save(user);
        return UserDto.from(user);
    }
}
