package com.JPA_Example.JPA_HandsOn.Service;

import com.JPA_Example.JPA_HandsOn.Entities.User;
import com.JPA_Example.JPA_HandsOn.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user){
        return userRepo.save(user);
    }

    public Page<User> getUsers(Integer page, Integer size) {
        int p = (page == null) ? 0 : page;
        int s = (size == null) ? 50 : size;

        // Validate constraints in SERVICE layer
        if (s < 1) {
            throw new IllegalArgumentException("size must be at least 1");
        }
        if (s > 50) {
            throw new IllegalArgumentException("size cannot exceed 50");
        }
        if (p < 0) {
            throw new IllegalArgumentException("page cannot be negative");
        }

        Pageable pageable = PageRequest.of(p, s);
        return userRepo.findAll(pageable);
    }
}
