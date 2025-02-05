package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Entity.User;
import com.backEndJavaSpring.Chatop_app.Mapper.UserMapper;
import com.backEndJavaSpring.Chatop_app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserDto> users() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addUser(UserDto user) {
        userRepository.save(userMapper.toEntity(user));

    }

    @Override
    public void updateUser(UserDto user) {
        userRepository.save(userMapper.toEntity(user));
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
        return userMapper.toDto(userRepository.findByEmail(email));
    }
}
