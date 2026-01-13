package com.HospitalStaff.Application.DTOs;

public record ShiftTypeRequest(
        String name,
        String description,
        Long tenantId
) {}