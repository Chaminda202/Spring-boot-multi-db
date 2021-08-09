package com.spring.multi.db.service.thirdparty.impl;

import com.spring.multi.db.model.thirdparty.User;
import com.spring.multi.db.repository.thirdparty.UserRepository;
import com.spring.multi.db.service.thirdparty.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
