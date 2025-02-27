package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.AuthRequest;
import com.backEndJavaSpring.Chatop_app.Dto.AuthResponse;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> users();
    AuthResponse addUser (UserDto user);
    UserDto updateUser (UserDto user);
    void deleteUser (UserDto user);
    UserDto getUserById(Long id);
    UserDto getUserByLoginAndPass(String login,String pass);
    UserDto getUserByEmail(String email);
    UserDto getLoggedUser();
    AuthResponse loginUser(AuthRequest authRequest);

}
