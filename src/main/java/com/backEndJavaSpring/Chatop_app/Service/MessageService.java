package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Dto.MessageResponse;

import java.util.List;

public interface MessageService {
    MessageResponse addMessage (MessageDto message);
}
