package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Service.JwtUtil;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
    @PostMapping("auth/register")
    public UserDto createUser(@RequestBody UserDto user) {
        System.out.print(user.toString());
        user.setPassword(encoder.encode(user.getPassword()));
        return  s.addUser(user);
    }
    @GetMapping("auth/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = s.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("auth/me")
    public ResponseEntity<UserDto> logged() {
        // Récupérer l'email de l'utilisateur connecté depuis le SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Le nom correspond à l'email ici
        // Récupérer les informations de l'utilisateur à partir du service
        UserDto user = s.getUserByEmail(email);

        return ResponseEntity.ok(user);
    }
}
@Getter
@Setter
class AuthRequest {
    private String  email;
    private String password;

}

class AuthResponse {
    private String token;
    public AuthResponse(String token) { this.token = token; }
    public String getToken() { return token; }
}