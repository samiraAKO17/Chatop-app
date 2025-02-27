package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;
import com.backEndJavaSpring.Chatop_app.Dto.RentalRequest;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Entity.Rental;
import com.backEndJavaSpring.Chatop_app.Mapper.RentalMapper;
import com.backEndJavaSpring.Chatop_app.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
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
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);

        // Convertir RentalRequest en RentalDto
        RentalDto dto = new RentalDto();
        dto=rentalMapper.rentalDto(request);
        dto.setOwner_id(user.getId());

        // Gérer l'upload de l'image ici (sauvegarde sur le serveur)
        if (request.getPicture() != null && !request.getPicture().isEmpty()) {
            String imageUrl = "saveImage()";
            // Chargement de l'image
            try {
                imageUrl = cloudinaryService.uploadImage(request.getPicture());
                System.out.println(imageUrl);
                dto.setPicture(imageUrl);
            } catch (IOException e) {

            }
        }
        return rentalMapper.toDto(rentalRepository.save(rentalMapper.toEntity(dto)));
    }

    @Override
    public RentalDto updateRental(RentalRequest request, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);

        // Convertir RentalRequest en RentalDto
        RentalDto dto = getRentalById(id);
        dto =rentalMapper.rentalDto(request);
        dto.setOwner_id(user.getId());

        Rental rentalEntity = rentalMapper.toEntity(dto);
        Rental rentalToUpdate = rentalRepository.findById(rentalEntity.getId()).get();
        rentalToUpdate.setName(rentalEntity.getName());
        rentalToUpdate.setOwner(rentalEntity.getOwner());
        rentalToUpdate.setPrice(rentalEntity.getPrice());
        rentalToUpdate.setSurface(rentalEntity.getSurface());
        rentalToUpdate.setDescription(rentalEntity.getDescription());
        rentalToUpdate.setCreated_at(rentalEntity.getCreated_at());
        rentalToUpdate.setUpdated_at(rentalEntity.getUpdated_at());
        return rentalMapper.toDto(rentalRepository.save(rentalToUpdate));
    }

    @Override
    public void deleteRental(RentalDto rental) {
        rentalRepository.delete(rentalMapper.toEntity(rental));
    }

    @Override
    public RentalDto getRentalById(Long id) {

        return rentalMapper.toDto(rentalRepository.existsById(id) ? rentalRepository.findById(id).get() : null);
    }
}
