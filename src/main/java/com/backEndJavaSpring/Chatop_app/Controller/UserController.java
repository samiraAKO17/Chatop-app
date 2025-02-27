package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.AuthRequest;
import com.backEndJavaSpring.Chatop_app.Dto.AuthResponse;
import com.backEndJavaSpring.Chatop_app.Dto.RentalResponse;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Service.JwtUtil;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import io.jsonwebtoken.io.IOException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RequestMapping("api")
@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService s;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    BCryptPasswordEncoder encoder;
    @PostMapping("auth/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        try {
            return ResponseEntity.ok(s.loginUser(request));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthResponse(""));
        }
    }
    @PostMapping("auth/register")
    public ResponseEntity<AuthResponse> createUser(@RequestBody UserDto request) {
        try {
            return ResponseEntity.ok(s.addUser(request));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthResponse(""));
        }
    }
    @GetMapping("auth/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = s.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("auth/me")
    public ResponseEntity<UserDto> logged() {
        try {
            return ResponseEntity.ok(s.getLoggedUser());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserDto());
        }
    }
}