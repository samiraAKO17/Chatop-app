package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Dto.MessageResponse;
import com.backEndJavaSpring.Chatop_app.Service.MessageService;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("api")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("messages")
    public ResponseEntity<MessageResponse> createUser(@RequestBody MessageDto message) {

        return ResponseEntity.ok(messageService.addMessage(message));
    }
}