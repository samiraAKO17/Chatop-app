package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.*;
import com.backEndJavaSpring.Chatop_app.Service.CloudinaryService;
import com.backEndJavaSpring.Chatop_app.Service.RentalService;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class RentalController {
    @Autowired
    private RentalService s;
    @Autowired
    private UserService userService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("rentals")
    public ResponseEntity<RentalResponse> createRental(@ModelAttribute RentalForm rentalForml) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);

        // Convertir RentalForm en RentalDto
        RentalDto rental = new RentalDto();
        rental.setName(rentalForml.getName());
        rental.setSurface(rentalForml.getSurface());
        rental.setPrice(rentalForml.getPrice());
        rental.setDescription(rentalForml.getDescription());
        rental.setOwner_id(user.getId());

        // Gérer l'upload de l'image ici (sauvegarde sur le serveur)
        if (rentalForml.getPicture() != null && !rentalForml.getPicture().isEmpty()) {
            String imageUrl = "saveImage()";
            // Chargement de l'image
            try {
                imageUrl = cloudinaryService.uploadImage(rentalForml.getPicture());
                System.out.println(imageUrl);
                rental.setPicture(imageUrl);
            } catch (IOException e) {
                return ResponseEntity.status(500).body(new RentalResponse("erreur lors du chargement de l'image"));
            }
        }
        s.addRental(rental);
        return ResponseEntity.ok(new RentalResponse("Rental created !"));
    }
    @GetMapping("rentals")
    public ResponseEntity<RentalsResponse> getAll(){
        return ResponseEntity.ok(new RentalsResponse(s.rentals()));
    }
    @PutMapping("rentals/{id}")
    public ResponseEntity<RentalResponse> UpdatetRental(@ModelAttribute RentalForm rentalForml, @PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);

        // Convertir RentalForm en RentalDto
        RentalDto rental = s.getRentalById(id);
        rental.setName(rentalForml.getName());
        rental.setSurface(rentalForml.getSurface());
        rental.setPrice(rentalForml.getPrice());
        rental.setDescription(rentalForml.getDescription());
        rental.setOwner_id(user.getId());

        s.updateRental(rental);
        return ResponseEntity.ok(new RentalResponse("Rental updated !"));
    }
    @GetMapping("rentals/{id}")
    public ResponseEntity<RentalDto> getRental( @PathVariable Long id) {
        System.out.print(id);
        RentalDto rental= s.getRentalById(id);
        return ResponseEntity.ok(rental);
    }
}