package com.JPA_Example.JPA_HandsOn.Controller;

import com.JPA_Example.JPA_HandsOn.Entities.Shift;
import com.JPA_Example.JPA_HandsOn.Models.ShiftDTO;
import com.JPA_Example.JPA_HandsOn.Service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shift")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @PostMapping
    public ResponseEntity<Shift> addShift(@RequestBody ShiftDTO shiftDTO) {
        return new ResponseEntity<>(shiftService.addShift(shiftDTO), HttpStatus.CREATED);
    }

    @GetMapping("/shifts/new-year-top3")
    public List<Shift> getTop3NewYearShifts() {
        return shiftService.getTop3NewYearShifts();
    }
}
