package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Entity.User;

import java.util.List;

public interface UserService {
    List<User> users();
    void addUser (User user);
    void updateUser (User user);
    void deleteUser (User user);
    User getUserById(Long id);
    User getUserByLoginAndPass(String login,String pass);
    User getUserByEmail(String email);
}
