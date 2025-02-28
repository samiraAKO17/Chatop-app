package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.*;
import com.backEndJavaSpring.Chatop_app.Exception.ResourceNotFoundException;
import com.backEndJavaSpring.Chatop_app.Service.CloudinaryService;
import com.backEndJavaSpring.Chatop_app.Service.RentalService;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class RentalController {
    @Autowired
    private RentalService s;
    @Autowired
    private UserService userService;

    @PostMapping("rentals")
    public ResponseEntity<RentalResponse> createRental(@ModelAttribute RentalRequest request) {

        RentalDto response =s.addRental(request);
        if (response==null) {
            throw new IllegalArgumentException("Rental not created");
        }
        return ResponseEntity.ok(new RentalResponse("Rental created!"));

    }
    @GetMapping("rentals")
    public ResponseEntity<RentalsResponse> getAll(){
        return ResponseEntity.ok(new RentalsResponse(s.rentals()));
    }
    @PutMapping("rentals/{id}")
    public ResponseEntity<RentalResponse> UpdateRental(@ModelAttribute RentalRequest rental, @PathVariable Long id) {

        RentalDto response = s.updateRental(rental,id);
        if (response==null) {
            throw new ResourceNotFoundException("Rental not updated");
        }
        return ResponseEntity.ok(new RentalResponse("Rental updated !"));
    }
    @GetMapping("rentals/{id}")
    public ResponseEntity<RentalDto> getRental( @PathVariable Long id) {
        RentalDto rental= s.getRentalById(id);
        if (rental==null) {
            throw new IllegalArgumentException("Rental not found");
        }
        return ResponseEntity.ok(rental);
    }
}