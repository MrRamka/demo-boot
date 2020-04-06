package com.ybcompany.demo.api;

import com.ybcompany.demo.dto.SignUpDto;
import com.ybcompany.demo.dto.api.ResponseUserDto;
import com.ybcompany.demo.dto.api.ResponseUsersDto;
import com.ybcompany.demo.interfaces.LogExecutionTime;
import com.ybcompany.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users-management")
public class UserRestController {

    @Autowired
    private UserService userService;

    @LogExecutionTime
    @GetMapping("/users")
    public ResponseUsersDto getUsers() {
        return ResponseUsersDto
                .builder()
                .data(userService.getAllUsers())
                .build();
    }


    @PostMapping("/users")
    public ResponseUserDto addUser(@RequestBody SignUpDto userData) {
        return ResponseUserDto
                .builder()
                .userDto(userService.addUser(userData))
                .build();

    }

}
