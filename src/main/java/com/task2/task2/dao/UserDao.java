package com.task2.task2.dao;
import com.task2.task2.Entity.User;
import java.time.LocalDateTime;
import java.util.List;
public interface UserDao {
    void save(User user);
    List<User> findInactiveUsers(LocalDateTime sixMonthsAgo);
    void deleteAll(List<User> users);
}
