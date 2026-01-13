package com.HospitalStaff.Application.Controller;

import com.HospitalStaff.Application.DTOs.UserRequest;
import com.HospitalStaff.Application.DTOs.UserResponse;
import com.HospitalStaff.Application.Service.ShiftTypeService;
import com.HospitalStaff.Application.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String createUser(@RequestBody UserRequest request) {
        userService.createUser(request);
        return "User created successfully";
    }

    @GetMapping("/{tenantId}")
    public List<Map<String, Object>> getUsersByTenant(@PathVariable Long tenantId) {
        return userService.getUsersByTenant(tenantId);
    }

    @GetMapping("/shifts/{tenantId}")
    public List<Map<String, Object>> getShiftsByTenant(@PathVariable Long tenantId) {
        return userService.getShiftsByTenant(tenantId);
    }

    @PutMapping("/{userId}")
    public String updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest request
    ) {
        return userService.updateUser(userId,request);
    }

    @GetMapping
    public List<UserResponse> getUsers(
            @RequestParam Long tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        return userService.getUsersByTenant(tenantId, page, size, sort);
    }

}
