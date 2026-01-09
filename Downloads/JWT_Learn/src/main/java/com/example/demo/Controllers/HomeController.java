package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService ;

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUser(){
        System.out.println("getting Users");
        return userService.getUsers();
    }

    @GetMapping("/current-user")
    @PreAuthorize("hasRole('BASIC')")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
