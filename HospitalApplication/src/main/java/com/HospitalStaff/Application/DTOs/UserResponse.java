package com.HospitalStaff.Application.DTOs;

public record UserResponse(
        Long id,
        String username,
        String timezone,
        Long tenantId
) {}