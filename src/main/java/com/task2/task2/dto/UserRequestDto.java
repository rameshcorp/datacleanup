package com.task2.task2.dto;

import com.task2.task2.Entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRequestDto {
    private String name;              // User name
    private String email;             // User email
    private LocalDateTime lastLoginDate; // Last login date for cleanup criteria
}
