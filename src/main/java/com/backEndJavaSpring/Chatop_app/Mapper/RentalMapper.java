package com.backEndJavaSpring.Chatop_app.Mapper;


import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;
import com.backEndJavaSpring.Chatop_app.Entity.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {
    UserMapper userMapper = new UserMapper();
    public RentalDto toDto(Rental rental) {
        RentalDto dto = new RentalDto();
        dto.setId(rental.getId());
        dto.setName(rental.getName());
        dto.setOwner(userMapper.toDto(rental.getOwner()));
        dto.setPrice(rental.getPrice());
        dto.setSurface(rental.getSurface());
        dto.setPicture(rental.getPicture());
        dto.setDescription(rental.getDescription());
        dto.setCreated_at(rental.getCreated_at());
        dto.setUpdated_at(rental.getUpdated_at());
        return dto;
    }

    public Rental toEntity(RentalDto dto) {
        Rental rental = new Rental();
        rental.setId(dto.getId());
        rental.setName(dto.getName());
        rental.setOwner(userMapper.toEntity(dto.getOwner()));
        rental.setPrice(dto.getPrice());
        rental.setSurface(dto.getSurface());
        rental.setDescription(dto.getDescription());
        rental.setCreated_at(dto.getCreated_at());
        rental.setUpdated_at(dto.getUpdated_at());
        return rental;
    }
}
