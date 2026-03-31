package com.example.demo.controller;

import com.example.demo.config.JwtUtil;

import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User; 

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")

public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    // Constructor Injection
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> user) {

        String username = user.get("username");
        String password = user.get("password");
        
        //Authenticate using Spring Security
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)
        );
        User userUser = userRepository.findByUsername(username)
                        .orElseThrow(()-> new RuntimeException("User not found"));
        String token = jwtUtil.generateToken(userUser.getUsername(),userUser.getRole()); 
        return Map.of("token", token);
        


    }
}