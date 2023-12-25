package com.example.odev.webApiControllers;

import com.example.odev.Entity.Role;
import com.example.odev.Entity.User;
import com.example.odev.Repository.UserRepository;
import com.example.odev.business.abstracts.UserService;
import com.example.odev.business.auth.JwtUtil;
import com.example.odev.business.requests.LoginRequest;
import com.example.odev.business.responses.ErrorResponse;
import com.example.odev.business.responses.LoginResponse;
import com.example.odev.business.responses.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            String username = authentication.getName();
            User user = userRepository.findUserByUsername(username);
            String token = jwtUtil.createToken(user);
            LoginResponse loginResponse = new LoginResponse(username, token, user.getEmail(),user.getRole().getId());

            return ResponseEntity.ok(loginResponse);
        } catch (BadCredentialsException badCredentialsException) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Hatalı şifre ya da kullanıcı adı");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception exceptionn) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, exceptionn.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
