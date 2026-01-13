package com.HospitalStaff.Application.Service;

import com.HospitalStaff.Application.DTOs.ShiftTypeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShiftTypeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ShiftTypeService.class);

    public int createShiftType(ShiftTypeRequest request) {

        logger.info("Catering to ShiftTypeRequest into Shift_Type table");

        String sql = """
            INSERT INTO shift_type (name, description, tenant_id)
            VALUES (?, ?, ?)
        """;

        return jdbcTemplate.update(
                sql,
                request.name(),
                request.description(),
                request.tenantId()
        );
    }
}

