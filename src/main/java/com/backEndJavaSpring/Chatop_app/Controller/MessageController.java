package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Entity.Message;
import com.backEndJavaSpring.Chatop_app.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MessageController {
    @Autowired
    private MessageService s;

    @PostMapping("Messages")
    public MessageDto createUser(@RequestBody MessageDto message) {
        System.out.print(message.toString());
        s.addMessage(message);
        return message;
    }
}
