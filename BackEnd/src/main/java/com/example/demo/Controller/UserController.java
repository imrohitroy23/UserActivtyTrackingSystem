package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Controller.Dto.LogOutRequest;
import com.example.demo.Controller.Dto.LoginRequest;
import com.example.demo.Model.User;
import com.example.demo.Model.UserLog;
import com.example.demo.Service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {

        String userAgent = request.getHeader("User-Agent");
        String osName =request.getHeader("User-Agent");
        String osName1 = "Unknown";
        if (osName != null) {
            if (osName.toLowerCase().contains("windows")) {
                osName1 = "Windows";
            } else if (osName.toLowerCase().contains("mac")) {
                osName1 = "Mac";
            } else if (osName.toLowerCase().contains("linux")) {
                osName1 = "Linux";
            } else if (osName.toLowerCase().contains("android")) {
                osName1 = "Android";
            } else if (osName.toLowerCase().contains("iphone")) {
                osName1 = "iOS";
            }
        }
        String browserName1 = "Unknown";
        if (userAgent != null) {
            if (userAgent.contains("MSIE")) {
                browserName1 = "Internet Explorer";
            } else if (userAgent.contains("firefox")) {
                browserName1 = "Mozilla firefox";
            } else if (userAgent.contains("Edg")) {
                browserName1 = "Microsoft Edge";
            } else if (userAgent.contains("Chrome")) {
                browserName1 = "Google Chrome";
            } else if (userAgent.contains("Safari")) {
                browserName1 = "Apple Safari";
            } else if (userAgent.contains("Firefox")) {
                browserName1 = "Mozilla Firefox";
            }
        }
      
        return userService.login(loginRequest.getUsername(), loginRequest.getPassword(), browserName1, osName1, loginRequest.getIpAddress());
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogOutRequest logoutRequest) {
        userService.logout(logoutRequest.getId());
    }
    
    @GetMapping("/user-logs")
    public List<UserLog> getUserLogs() {
        return userService.getUserLogs();
    }
}