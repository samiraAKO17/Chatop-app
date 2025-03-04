package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.AuthRequest;
import com.backEndJavaSpring.Chatop_app.Dto.AuthResponse;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;

public interface UserService {
    AuthResponse addUser (UserDto user);
    UserDto getUserById(Long id);
    UserDto getUserByLoginAndPass(String login,String pass);
    UserDto getUserByEmail(String email);
    UserDto getLoggedUser();
    AuthResponse loginUser(AuthRequest authRequest);

}
