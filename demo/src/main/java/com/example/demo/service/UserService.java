package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

    public User createUser(User user){


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = this.userRepository.save(user);
        return  savedUser;
    }

    public boolean getUser(User user1){

        User user = this.userRepository.findByName(user1.getName());
        if(user!= null && user.getName().equals(user1.getName()) && passwordEncoder.matches(user1.getPassword(),user.getPassword()))return true;
        return false;
    }
}
