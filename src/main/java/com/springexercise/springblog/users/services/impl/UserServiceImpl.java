package com.springexercise.springblog.users.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springexercise.springblog.users.entities.User;
import com.springexercise.springblog.users.repositories.UserRepository;
import com.springexercise.springblog.users.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
}
