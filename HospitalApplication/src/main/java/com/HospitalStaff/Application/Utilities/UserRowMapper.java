package com.HospitalStaff.Application.Utilities;

import com.HospitalStaff.Application.DTOs.UserResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<UserResponse> {

    @Override
    public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserResponse(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("timezone"),
                rs.getLong("tenant_id")
        );
    }
}

