package com.task2.task2.service;
import com.task2.task2.Entity.User;
import com.task2.task2.dao.UserDao;
import com.task2.task2.dto.UserRequestDto;
import com.task2.task2.dto.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setLastLoginDate(userRequestDto.getLastLoginDate() != null ?
                userRequestDto.getLastLoginDate() : LocalDateTime.now());
        user.setCreatedAt(LocalDateTime.now());  // Set createdAt to current time
        userDao.save(user);
        // Create response DTO
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setMessage("User created successfully.");

        return responseDto;
    }
    @Override
    @Scheduled(cron = "0 0 0 * * *")  // Runs every day at midnight
    public void cleanInactiveUsers() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<User> inactiveUsers = userDao.findInactiveUsers(sixMonthsAgo);

        int deletedCount = inactiveUsers.size();
        userDao.deleteAll(inactiveUsers);

        logger.info("User cleanup task completed. Users deleted: {}", deletedCount);
    }

}
