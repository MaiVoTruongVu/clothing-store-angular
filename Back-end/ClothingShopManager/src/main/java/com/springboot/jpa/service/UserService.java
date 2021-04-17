package com.springboot.jpa.service;


import java.util.List;

import com.springboot.jpa.models.User;
import com.springboot.jpa.models.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(Integer id);
    User findOne(String username);

    User findById(Integer id);
}
