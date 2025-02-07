package com.backEndJavaSpring.Chatop_app.Mapper;

import com.backEndJavaSpring.Chatop_app.Dto.MessageDto;
import com.backEndJavaSpring.Chatop_app.Entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    UserMapper userMapper = new UserMapper();
    RentalMapper rentalMapper = new RentalMapper();
    public MessageDto toDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setMessage(message.getMessage());
        dto.setUser(userMapper.toDto(message.getUser()));
        dto.setRental(rentalMapper.toDto(message.getRental()));
        dto.setCreated_at(message.getCreated_at());
        dto.setUpdated_at(message.getUpdated_at());
        return dto;
    }

    public Message toEntity(MessageDto dto) {
        Message message = new Message();
        message.setId(dto.getId());
        message.setMessage(dto.getMessage());
        message.setUser(userMapper.toEntity(dto.getUser()));
        message.setRental(rentalMapper.toEntity(dto.getRental()));
        message.setCreated_at(dto.getCreated_at());
        message.setUpdated_at(dto.getUpdated_at());
        return message;
    }
}
