package com.HospitalStaff.Application.DTOs;

public record UserRequest(
        String username,
        String timezone,
        Long tenantId
) {}