package com.example.demo.Models;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {

    private String userName;
    private String password;
    private String email;
    private List<String> roles; // e.g., "ROLE_BASIC", "ROLE_ADMIN"

    public User(String userName, String password, String mail) {
        this.userName = userName;
        this.password = password;
        this.email = mail;
        this.roles = List.of("BASIC");
    }
}