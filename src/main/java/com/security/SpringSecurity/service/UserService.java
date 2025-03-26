package com.security.SpringSecurity.service;

import com.security.SpringSecurity.entities.Users;
import com.security.SpringSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Users register(Users users)
    {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepo.save(users);
    }
}
