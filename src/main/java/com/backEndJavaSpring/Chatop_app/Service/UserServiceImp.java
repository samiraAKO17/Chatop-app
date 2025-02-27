package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.AuthRequest;
import com.backEndJavaSpring.Chatop_app.Dto.AuthResponse;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Entity.User;
import com.backEndJavaSpring.Chatop_app.Mapper.UserMapper;
import com.backEndJavaSpring.Chatop_app.Repository.RentalRepository;
import com.backEndJavaSpring.Chatop_app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private RentalRepository rentalRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<UserDto> users() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthResponse addUser(UserDto user) {
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
    public UserDto updateUser(UserDto user) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public void deleteUser(UserDto user) {
        userRepository.delete(userMapper.toEntity(user));
    }

    @Override
    public UserDto getUserById(Long id) {
       User  s = userRepository.findById(id).orElse(null);
        return userMapper.toDto(s);
    }

    @Override
    public UserDto getUserByLoginAndPass(String login, String pass) {
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserDto dto = null;
        Optional<User> user =userRepository.findByEmail(email);
        if(user.isPresent())
        dto = userMapper.toDto(user.get());
        return dto;
    }

    @Override
    public UserDto getLoggedUser() {
        // Récupérer l'email de l'utilisateur connecté depuis le SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Le nom correspond à l'email ici
        // Récupérer les informations de l'utilisateur à partir du service
        return getUserByEmail(email);
    }

    @Override
    public AuthResponse loginUser(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(authRequest.getEmail());
        return new AuthResponse(token);
    }
}
