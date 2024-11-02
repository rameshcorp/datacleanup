package com.task2.task2.service;

import com.task2.task2.dto.UserRequestDto;
import com.task2.task2.dto.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    void cleanInactiveUsers();
}
