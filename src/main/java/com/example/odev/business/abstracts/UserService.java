package com.example.odev.business.abstracts;

import com.example.odev.Entity.User;
import com.example.odev.Repository.UserRepository;
import com.example.odev.business.responses.UserRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    void saveUserToDB(String username,String email,String password);


}

