package com.example.demo.Service;

import com.example.demo.Models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    List<User> list = new ArrayList<>();

    public UserService(){
        list.add(new User("Sanidhya","sandy","san@gmail.com"));
        list.add(new User("Aakash","aak","aak@gmail.com"));
        list.add(new User("Mathungi","math","math@gmail.com"));
        list.add(new User("Sanchit ","sanch","sanch@gmail.com"));
    }

    public List<User> getUsers(){
        return list;
    }
}
