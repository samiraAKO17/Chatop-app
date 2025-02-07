package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;
import com.backEndJavaSpring.Chatop_app.Entity.Message;
import com.backEndJavaSpring.Chatop_app.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MessageController {
    @Autowired
    private MessageService s;

    @PostMapping("Messages")
    public MessageDto createUser(@RequestBody MessageDto message) {
        System.out.print(message.toString());
        return s.addMessage(message);
    }
    @GetMapping("Messages/{id}")
    public MessageDto getMessage(@PathVariable Long id) {
        System.out.print(id);
        MessageDto messageDto= s.getMessageById(id);
        return messageDto;
    }
}
