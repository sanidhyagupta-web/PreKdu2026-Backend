package com.HospitalStaff.Application.Service;

import com.HospitalStaff.Application.DTOs.TenantOnboardingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenantOnboardingService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TenantOnboardingService.class);

    @Transactional
    public void onboardTenant(TenantOnboardingRequest request) {

        // 1 Insert Shift Types
        for (String shiftType : request.shiftTypes()) {
            jdbcTemplate.update("""
                INSERT INTO shift_type (name, tenant_id)
                VALUES (?, ?)
            """, shiftType, request.tenantId());
        }

        // 2 Insert Shifts (dummy shifts)
        jdbcTemplate.update("""
            INSERT INTO shift (
                shift_type_id, start_date, end_date,
                start_time, end_time, tenant_id
            )
            SELECT id, CURRENT_DATE, CURRENT_DATE,
                   '09:00:00', '17:00:00', tenant_id
            FROM shift_type
            WHERE tenant_id = ?
        """, request.tenantId());

        // 3 Insert Users
        for (String username : request.users()) {
            jdbcTemplate.update("""
                INSERT INTO users (username, timezone, tenant_id)
                VALUES (?, 'UTC', ?)
            """, username, request.tenantId());
        }

        // 4 Assign Users to Shifts
        jdbcTemplate.update("""
            INSERT INTO shift_user (shift_id, user_id, tenant_id)
            SELECT s.id, u.id, s.tenant_id
            FROM shift s
            JOIN users u ON s.tenant_id = u.tenant_id
            WHERE s.tenant_id = ?
        """, request.tenantId());

        // 5 FORCE FAILURE (Null violation)
        logger.error("Forced Failure");
        jdbcTemplate.update("""
            INSERT INTO users (username, tenant_id)
            VALUES (NULL, ?)
        """, request.tenantId());

        // CHECK THESE QUERIES TO VERIFY THAT NOTHING HAS BEEN SAVED
//        SELECT * FROM shift_type WHERE tenant_id = 99;
//        SELECT * FROM shift WHERE tenant_id = 99;
//        SELECT * FROM users WHERE tenant_id = 99;
//        SELECT * FROM shift_user WHERE tenant_id = 99;

    }
}

