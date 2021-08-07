package com.spring.multi.db.controller;

import com.spring.multi.db.user.model.User;
import com.spring.multi.db.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping
    public List<User> retrieveAllUsers() {
        return this.userRepository.findAll();
    }
}
