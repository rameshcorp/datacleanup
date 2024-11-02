package com.task2.task2.dao;

import com.task2.task2.Entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        // Include created_at in the insert statement
        String sql = "INSERT INTO users (name, email, last_login_date, is_active, created_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getLastLoginDate(), user.isActive(), user.getCreatedAt());
    }

    @Override
    public List<User> findInactiveUsers(LocalDateTime sixMonthsAgo) {
        String sql = "SELECT * FROM users WHERE last_login_date < ? AND is_active = true";
        return jdbcTemplate.query(sql, new Object[]{sixMonthsAgo}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setLastLoginDate(rs.getTimestamp("last_login_date").toLocalDateTime());
            user.setActive(rs.getBoolean("is_active"));
            user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime()); // Map createdAt from DB
            return user;
        });
    }

    @Override
    public void deleteAll(List<User> users) {
        String sql = "DELETE FROM users WHERE id = ?";
        for (User user : users) {
            jdbcTemplate.update(sql, user.getId());
        }
    }
}
