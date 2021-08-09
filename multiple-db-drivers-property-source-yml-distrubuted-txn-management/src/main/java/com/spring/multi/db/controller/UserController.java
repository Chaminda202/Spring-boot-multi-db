package com.spring.multi.db.controller;


import com.spring.multi.db.model.thirdparty.User;
import com.spring.multi.db.service.thirdparty.UserService;
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
    private final UserService userService;

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @GetMapping
    public List<User> retrieveAllUsers() {
        return this.userService.findAll();
    }
}
