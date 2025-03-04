package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.AuthRequest;
import com.backEndJavaSpring.Chatop_app.Dto.AuthResponse;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Entity.User;
import com.backEndJavaSpring.Chatop_app.Exception.ResourceNotFoundException;
import com.backEndJavaSpring.Chatop_app.Exception.UnauthorizedException;
import com.backEndJavaSpring.Chatop_app.Mapper.UserMapper;
import com.backEndJavaSpring.Chatop_app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse addUser(UserDto user) {
        // Vérifier si l'utilisateur existe déjà
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UnauthorizedException("user already exist");
        }
        UserDto dto = new UserDto();
        String email = user.getEmail();
        dto.setPassword(encoder.encode(user.getPassword()));
        dto.setEmail(email);
        dto.setName(user.getName());
        userRepository.save(userMapper.toEntity(dto));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), email)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);

    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with ID : " + id));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user not found : " + email));
        return userMapper.toDto(user);
    }

    @Override
    public AuthResponse loginUser(AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken(authRequest.getEmail());
            return new AuthResponse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Échec de l'authentification. Vérifiez vos identifiants.");
        }
    }

    @Override
    public UserDto getLoggedUser() {
        // Récupérer l'email de l'utilisateur connecté depuis le SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Le nom correspond à l'email ici
        // Récupérer les informations de l'utilisateur
        return getUserByEmail(email);
    }

    @Override
    public UserDto getUserByLoginAndPass(String login, String pass) {

        return null;
    }

}
