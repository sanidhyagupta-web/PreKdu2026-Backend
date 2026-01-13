package com.HospitalStaff.Application.Controller;

import com.HospitalStaff.Application.DTOs.TenantOnboardingRequest;
import com.HospitalStaff.Application.Service.TenantOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/onboard")
public class TenantOnboardingController {

    @Autowired
    private TenantOnboardingService service;

    @PostMapping
    public String onboard(@RequestBody TenantOnboardingRequest request) {
        service.onboardTenant(request);
        return "Tenant onboarded";
    }


}

