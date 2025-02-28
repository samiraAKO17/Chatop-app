package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Dto.MessageResponse;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Exception.ResourceNotFoundException;
import com.backEndJavaSpring.Chatop_app.Service.MessageService;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@RequestMapping("api")
@RestController
public class MessageController {
    @Autowired
    private MessageService s;
    @Autowired
    private UserService userService;

    @PostMapping("messages")
    public ResponseEntity<MessageResponse> createUser(@RequestBody MessageDto message) {

        MessageResponse response = s.addMessage(message);
        if (response == null) {
            throw new ResourceNotFoundException("");
        }
        return ResponseEntity.ok(response);

    }
}