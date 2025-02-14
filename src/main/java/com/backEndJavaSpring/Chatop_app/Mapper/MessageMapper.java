package com.backEndJavaSpring.Chatop_app.Mapper;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Entity.Message;
import com.backEndJavaSpring.Chatop_app.Service.RentalService;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RentalService rentalService;
    public MessageDto toDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setMessage(message.getMessage());
        dto.setUser_id(message.getUser().getId());
        dto.setRental_id(message.getRental().getId());
        dto.setCreated_at(message.getCreated_at());
        dto.setUpdated_at(message.getUpdated_at());
        return dto;
    }

    public Message toEntity(MessageDto dto) {
        Message message = new Message();
        message.setId(dto.getId());
        message.setMessage(dto.getMessage());
        message.setUser(userMapper.toEntity(userService.getUserById(dto.getUser_id())));
        message.setRental(rentalMapper.toEntity(rentalService.getRentalById(dto.getRental_id())));
        message.setCreated_at(dto.getCreated_at());
        message.setUpdated_at(dto.getUpdated_at());
        return message;
    }
}
