package com.example.demo.service;

//import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

//import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    // @Override
    // public UserDetails loadUserByUsername(String username)
    //         throws UsernameNotFoundException {

    //     User user = repo.findByUsername(username)
    //             .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    //     return new org.springframework.security.core.userdetails.User(
    //             user.getUsername(),
    //             user.getPassword(),
    //             List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
    //     );
    // }

//     @Override
//     public UserDetails loadUserByUsername(String username)
//         throws UsernameNotFoundException {

//             if ("admin".equals(username)) {
//                 UserDetails user = new org.springframework.security.core.userdetails.User(
//                 "admin",
//                 "$2a$10$Dow1Vv7u9hKxHq0XrL5Q8u8zX2wP7Yx8Kx3v1Z9Y1uY9lQZ2KxXyO",
//                 Collections.emptyList()
//         );
//         // ✅ Correct debug
//         System.out.println("Username: " + user.getUsername());
//         System.out.println("Password Hash: " + user.getPassword());

//         return user;
//     }
    
//     throw new UsernameNotFoundException("User not found");
// }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        com.example.demo.entity.User user= repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }

   
    
}