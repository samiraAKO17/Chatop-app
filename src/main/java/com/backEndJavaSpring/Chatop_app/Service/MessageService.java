package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Dto.MessageResponse;

import java.util.List;

public interface MessageService {
    List<MessageDto> messages();
    MessageResponse addMessage (MessageDto message);
    MessageDto updateMessage (MessageDto message);
    void deleteMessage (MessageDto message);
    MessageDto getMessageById(Long id);
}
