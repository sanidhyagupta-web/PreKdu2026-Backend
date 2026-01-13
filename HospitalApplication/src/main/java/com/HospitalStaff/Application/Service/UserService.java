package com.HospitalStaff.Application.Service;

import com.HospitalStaff.Application.DTOs.UserRequest;
import com.HospitalStaff.Application.DTOs.UserResponse;
import com.HospitalStaff.Application.Utilities.UserRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRowMapper rowMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final int MAX_PAGE_SIZE = 10;

    public int createUser(UserRequest request) {

        logger.info("Catering to User Request ");
        String sql = """
                    INSERT INTO users (username, timezone, tenant_id)
                    VALUES (?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                request.username(),
                request.timezone(),
                request.tenantId()
        );
    }

    public List<Map<String, Object>> getUsersByTenant(Long tenantId) {
        logger.info("Catering to get User by TanentId ");
        String sql = "SELECT * FROM users WHERE tenant_id = ?";
        return jdbcTemplate.queryForList(sql, tenantId);
    }

    public List<Map<String, Object>> getShiftsByTenant(Long tenantId) {
        logger.info("Catering to get Tanent by TanentId ");
        String sql = "SELECT * FROM shift WHERE tenant_id = ?";
        return jdbcTemplate.queryForList(sql, tenantId);
    }


    public String updateUser(
            Long userId,
            UserRequest request
    ) {
        logger.info("Catering Update User by TanentId ");
        String sql = """
                    UPDATE users
                    SET username = ?, timezone = ?
                    WHERE id = ? AND tenant_id = ?
                """;

        int updated = jdbcTemplate.update(
                sql,
                request.username(),
                request.timezone(),
                userId,
                request.tenantId()
        );

        return updated > 0 ? "User updated" : "User not found";
    }

    public List<UserResponse> getUsersByTenant(
            Long tenantId,
            int page,
            int size,
            String sortDir
    ) {

        logger.info("Catering to getUsersByTenant ");

        // 1 Enforce safe page size
        size = Math.min(size, MAX_PAGE_SIZE);

        // 2 Calculate OFFSET
        int offset = page * size;

        // 3 Validate sorting direction
        String order = "ASC";
        if ("desc".equalsIgnoreCase(sortDir)) {
            order = "DESC";
        }

        // 4 Native SQL with LIMIT + OFFSET
        String sql = """
                    SELECT id, username, timezone, tenant_id
                    FROM users
                    WHERE tenant_id = ?
                    ORDER BY username %s
                    LIMIT ? OFFSET ?
                """.formatted(order);

        return jdbcTemplate.query(
                sql,
                rowMapper,
                tenantId,
                size,
                offset
        );

    }
}
