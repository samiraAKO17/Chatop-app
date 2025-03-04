package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;
import com.backEndJavaSpring.Chatop_app.Dto.RentalRequest;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Entity.Rental;
import com.backEndJavaSpring.Chatop_app.Exception.ResourceNotFoundException;
import com.backEndJavaSpring.Chatop_app.Exception.UnauthorizedException;
import com.backEndJavaSpring.Chatop_app.Mapper.RentalMapper;
import com.backEndJavaSpring.Chatop_app.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalServiceImp implements RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Override
    public List<RentalDto> rentals() {
        return rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public RentalDto addRental(RentalRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("user not authenticated");
        }
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);

        RentalDto dto = rentalMapper.rentalDto(request);
        dto.setOwner_id(user.getId());

        if (request.getPicture() != null && !request.getPicture().isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadImage(request.getPicture());
                dto.setPicture(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException("Error while picture upload", e);
            }
        }

        Rental savedRental = rentalRepository.save(rentalMapper.toEntity(dto));
        return rentalMapper.toDto(savedRental);
    }

    @Override
    public RentalDto updateRental(RentalRequest request, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);

        RentalDto existingRental = getRentalById(id);
        if (existingRental == null) {
            throw new ResourceNotFoundException("Rental not found with ID : " + id);
        }

        RentalDto updatedRental = rentalMapper.rentalDto(request);
        updatedRental.setId(id);
        updatedRental.setOwner_id(user.getId());

        Rental savedRental = rentalRepository.save(rentalMapper.toEntity(updatedRental));
        return rentalMapper.toDto(savedRental);
    }

    @Override
    public RentalDto getRentalById(Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isEmpty()) {
            throw new ResourceNotFoundException("Rental not found with ID : " + id);
        }
        return rentalMapper.toDto(rental.get());
    }
}
