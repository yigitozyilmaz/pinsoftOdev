package com.example.odev.Repository;

import com.example.odev.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer>{
    User findUserByEmail (String email);
}
