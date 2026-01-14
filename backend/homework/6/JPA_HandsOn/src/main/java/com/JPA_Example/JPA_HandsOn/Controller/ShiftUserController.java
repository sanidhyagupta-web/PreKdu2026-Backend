package com.JPA_Example.JPA_HandsOn.Controller;

import com.JPA_Example.JPA_HandsOn.Entities.ShiftUser;
import com.JPA_Example.JPA_HandsOn.Service.ShiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shift-user")
public class ShiftUserController {

    @Autowired
    private ShiftUserService shiftUserService;

    @PostMapping
    public ResponseEntity<ShiftUser> addShiftUser(@RequestBody ShiftUser shiftUser) {
        return new ResponseEntity<>(shiftUserService.addShiftUser(shiftUser), HttpStatus.CREATED);
    }
}
