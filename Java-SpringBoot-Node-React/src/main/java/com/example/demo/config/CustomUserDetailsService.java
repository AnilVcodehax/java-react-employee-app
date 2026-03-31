// package com.example.demo.config;

// import org.springframework.security.core.userdetails.*;
// import org.springframework.stereotype.Service;

// import java.util.Collections;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    //     // TEMP (for testing)
    //     if ("admin".equals(username)) {
    //         return new User(
    //                 "admin",
    //                 "$2a$10$Dow1Vv7u9hKxHq0XrL5Q8u8zX2wP7Yx8Kx3v1Z9Y1uY9lQZ2KxXyO", // encoded "password"
    //                 Collections.emptyList()
    //         );
    //     }

    //     throw new UsernameNotFoundException("User not found");
    // }
// }