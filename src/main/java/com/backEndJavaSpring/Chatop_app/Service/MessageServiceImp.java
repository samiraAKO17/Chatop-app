package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Dto.MessageResponse;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Mapper.MessageMapper;
import com.backEndJavaSpring.Chatop_app.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImp implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserService userService;
    @Override
    public List<MessageDto> messages() {
        return messageRepository.findAll()
                .stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse addMessage(MessageDto message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        message.setUser_id(user.getId());
        messageRepository.save(messageMapper.toEntity(message));
        return new MessageResponse("Message send with success");
    }

    @Override
    public MessageDto updateMessage(MessageDto message) {
       return messageMapper.toDto(messageRepository.save(messageMapper.toEntity(message)));
    }

    @Override
    public void deleteMessage(MessageDto message) {
        messageRepository.delete(messageMapper.toEntity(message));
    }

    @Override
    public MessageDto getMessageById(Long id) {
        return messageMapper.toDto(messageRepository.existsById(id) ? messageRepository.findById(id).get() : null);
    }
}
