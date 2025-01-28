package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> messages();
    void addMessage (Message message);
    void updateMessage (Message message);
    void deleteMessage (Message message);
    Message getMessageById(Long id);
}
