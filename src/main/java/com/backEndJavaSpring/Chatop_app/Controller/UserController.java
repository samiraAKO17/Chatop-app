package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private UserService s;

   /* @Autowired
    BCryptPasswordEncoder encoder;*/

    @PostMapping("auth/register")
    public UserDto createUser(@RequestBody UserDto user) {
        System.out.print(user.toString());
       // user.setPassword(encoder.encode(user.getPassword()));
        s.addUser(user);
        return user;
    }

    @PostMapping("auth/login")
    public UserDto getUser(@RequestBody UserDto user) {
        System.out.print(user.toString());
        return user;
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = s.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/auth/me")
    public ResponseEntity<UserDto> logged(@PathVariable String email) {
        UserDto user = s.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
