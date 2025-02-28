package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.AuthRequest;
import com.backEndJavaSpring.Chatop_app.Dto.AuthResponse;
import com.backEndJavaSpring.Chatop_app.Dto.RentalResponse;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Exception.ResourceNotFoundException;
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

        AuthResponse response =   s.loginUser(request);
        if(response.getToken().isEmpty()){
            throw new ResourceNotFoundException("");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("auth/register")
    public ResponseEntity<AuthResponse> createUser(@RequestBody UserDto request) {

        AuthResponse response =s.addUser(request);
        if(response.getToken().isEmpty()){
            throw new ResourceNotFoundException("");
        }
        return ResponseEntity.ok(response);

    }
    @GetMapping("auth/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = s.getUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("");
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping("auth/me")
    public ResponseEntity<UserDto> logged() {

        UserDto user = s.getLoggedUser();
        if (user == null) {
            throw new ResourceNotFoundException("");
        }
        return ResponseEntity.ok(user);

    }
}