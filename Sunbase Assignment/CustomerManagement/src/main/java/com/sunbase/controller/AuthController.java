package com.sunbase.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.leucine.collegedirectory.model.Users;
import com.sunbase.model.LoginRequest;
import com.sunbase.model.User;
import com.sunbase.repository.UserRepository;
import com.sunbase.utils.JwtToken;
import com.sunbase.utils.SecurityDetails;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUserHandler(@RequestBody User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> logInUserHandler(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        Optional<User> opt = userRepository.findByEmail(loginRequest.getEmail());
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = opt.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        // Generate token
        String token = JwtToken.generateToken(user);

        // Prepare response with user details and token
        response.setHeader(SecurityDetails.JWT_HEADER, "Bearer " + token);

        return ResponseEntity.ok().body("Login successful");
    }


    
}