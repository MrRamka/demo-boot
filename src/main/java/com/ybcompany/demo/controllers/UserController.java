package com.ybcompany.demo.controllers;

import com.ybcompany.demo.dto.UserDto;
import com.ybcompany.demo.interfaces.LogExecutionTime;
import com.ybcompany.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @LogExecutionTime
    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users_page";
    }
}
