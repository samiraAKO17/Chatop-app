package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.AuthRequest;
import com.backEndJavaSpring.Chatop_app.Dto.AuthResponse;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("api")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("auth/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {

        AuthResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("auth/register")
    public ResponseEntity<AuthResponse> createUser(@RequestBody UserDto request) {

        AuthResponse response = userService.addUser(request);
        return ResponseEntity.ok(response);

    }
    @GetMapping("auth/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("auth/me")
    public ResponseEntity<UserDto> logged() {

        UserDto user = userService.getLoggedUser();
        return ResponseEntity.ok(user);

    }
}