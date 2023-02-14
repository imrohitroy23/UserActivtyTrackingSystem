package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Model.UserLog;
import com.example.demo.Repository.UserLogRepo;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLogRepo userLogRepository;



    public User login(String username, String password, String browserName, String osName, String ipAddress) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }

        user.setLoggedIn(true);
        user.setLastLogin(new Date());
        userRepository.save(user);

        UserLog userLog = new UserLog();
        userLog.setUser(user);
        userLog.setBrowserName(browserName);
        userLog.setOsName(osName);
        userLog.setIpAddress(ipAddress);
        userLog.setLoginTime(new Date());
        userLogRepository.save(userLog);

        return user;
    }

    public void logout(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setLoggedIn(false);
            userRepository.save(user);

            Optional<UserLog> userLogOptional = userLogRepository.findFirstByUserAndLogoutTimeIsNullOrderByLoginTimeDesc(user);
            if (userLogOptional.isPresent()) {
                UserLog userLog = userLogOptional.get();
                userLog.setLogoutTime(new Date());
                long sessionDurationMillis = userLog.getLogoutTime().getTime() - userLog.getLoginTime().getTime();
                long sessionDurationMinutes = sessionDurationMillis / 60000;
                userLog.setSessionDuration(sessionDurationMinutes);
                userLogRepository.save(userLog);
            }
        }
    }

    public List<UserLog> getUserLogs() {
        return userLogRepository.findAll();
    }

}