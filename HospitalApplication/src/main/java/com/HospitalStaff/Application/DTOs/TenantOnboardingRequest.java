package com.HospitalStaff.Application.DTOs;

import java.util.List;

public record TenantOnboardingRequest(
        Long tenantId,
        List<String> shiftTypes,
        List<String> users
) {}