package com.ybcompany.demo.controllers;

import com.ybcompany.demo.dto.SignUpDto;
import com.ybcompany.demo.models.User;
import com.ybcompany.demo.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService service;

    @PreAuthorize("permitAll()")
    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute("form") SignUpDto form, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            model.addAttribute("noErrors", true);
            service.signUp(form);
            return "redirect:/signUp";
        } else {
            model.addAttribute("form", form);
            return "sign_up";
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/signUp")
    public String getSignUpPage(Model map) {
        map.addAttribute("form", new SignUpDto());
        return "sign_up";

    }
}
