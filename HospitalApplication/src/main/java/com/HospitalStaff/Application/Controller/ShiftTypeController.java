package com.HospitalStaff.Application.Controller;

import com.HospitalStaff.Application.DTOs.ShiftTypeRequest;
import com.HospitalStaff.Application.Service.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shift-types")
public class ShiftTypeController {

    @Autowired
    private ShiftTypeService service;

    @PostMapping
    public String createShiftType(@RequestBody ShiftTypeRequest request) {
        service.createShiftType(request);
        return "Shift type created";
    }
}

