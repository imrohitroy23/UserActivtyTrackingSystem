package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;
import com.example.demo.Model.UserLog;


@Repository
public interface UserLogRepo extends JpaRepository<UserLog,Long>{
    
    Optional<UserLog> findFirstByUserAndLogoutTimeIsNullOrderByLoginTimeDesc(User user);
    
}
