package com.JPA_Example.JPA_HandsOn.Controller;


import com.JPA_Example.JPA_HandsOn.Entities.ShiftType;
import com.JPA_Example.JPA_HandsOn.Models.ShiftTypeDTO;
import com.JPA_Example.JPA_HandsOn.Service.ShiftTypeService;
import com.JPA_Example.JPA_HandsOn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shift-types")
public class ShiftTypeController {

    @Autowired
    private ShiftTypeService shiftTypeService;

    @PostMapping
    public ResponseEntity<ShiftType> addUser(@RequestBody ShiftTypeDTO shiftTypeDTO) {
        return new ResponseEntity<>(shiftTypeService.addShift(shiftTypeDTO), HttpStatus.CREATED);
    }
}

