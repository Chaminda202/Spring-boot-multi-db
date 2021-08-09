package com.spring.multi.db.service.thirdparty;

import com.spring.multi.db.model.thirdparty.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> findAll();
}
