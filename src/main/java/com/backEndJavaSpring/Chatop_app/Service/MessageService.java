package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> messages();
    void addMessage (MessageDto message);
    void updateMessage (MessageDto message);
    void deleteMessage (MessageDto message);
    MessageDto getMessageById(Long id);
}
