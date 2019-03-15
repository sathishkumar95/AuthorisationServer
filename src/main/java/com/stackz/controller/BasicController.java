package com.stackz.controller;

import com.stackz.model.User;
import com.stackz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BasicController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/saveuser")
    public User saveUser(@RequestParam("name") String name) {
        User user = new User();
        user.setName(name);
        user.setUsername(name);
        user.setPassword(passwordEncoder.encode("password"));
        return userRepository.save(user);
    }

    @GetMapping("/getusers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
