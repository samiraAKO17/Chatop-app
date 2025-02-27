package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Dto.MessageSuccess;
import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Entity.Message;
import com.backEndJavaSpring.Chatop_app.Service.MessageService;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    public ResponseEntity<MessageSuccess > createUser(@RequestBody MessageDto message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        message.setUser_id(user.getId());
        s.addMessage(message);
        return ResponseEntity.ok(new MessageSuccess("Message send with success"));
    }
    @GetMapping("messages/{id}")
    public MessageDto getMessage(@PathVariable Long id) {
        System.out.print(id);
        MessageDto messageDto= s.getMessageById(id);
        return messageDto;
    }
}