package com.ybcompany.demo.services;

import com.ybcompany.demo.dto.SignUpDto;
import com.ybcompany.demo.models.Role;
import com.ybcompany.demo.models.User;
import com.ybcompany.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Saving user SignUpDto form
     *
     * @param form
     */
    @Override
    public void signUp(SignUpDto form) {
        User user = User.builder()
                .username(form.getUsername())
                .fullName(form.getFullName())
                .createdAt(LocalDateTime.now())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }
}
