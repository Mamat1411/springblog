package com.springexercise.springblog.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springexercise.springblog.users.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
