package com.security.SpringSecurity.service;

import com.security.SpringSecurity.entities.Users;
import com.security.SpringSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Users register(Users users)
    {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

    public String verify(Users users) {

        Authentication authentication = authenticationManager.authenticate(
         new UsernamePasswordAuthenticationToken(
                 users.getUsername(),
                 users.getPassword()
         )
        );

        if(authentication.isAuthenticated()) return jwtService.generateToken(users.getUsername());

        return "Failure";

    }
}
