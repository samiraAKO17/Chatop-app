package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.*;
import com.backEndJavaSpring.Chatop_app.Service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @PostMapping("rentals")
    public ResponseEntity<RentalResponse> createRental(@ModelAttribute RentalRequest request) {

        RentalDto response =rentalService.addRental(request);
        return ResponseEntity.ok(new RentalResponse("Rental created!"));

    }
    @GetMapping("rentals")
    public ResponseEntity<RentalsResponse> getAll(){
        return ResponseEntity.ok(new RentalsResponse(rentalService.rentals()));
    }
    @PutMapping("rentals/{id}")
    public ResponseEntity<RentalResponse> UpdateRental(@ModelAttribute RentalRequest rental, @PathVariable Long id) {

        RentalDto response = rentalService.updateRental(rental,id);
        return ResponseEntity.ok(new RentalResponse("Rental updated !"));
    }
    @GetMapping("rentals/{id}")
    public ResponseEntity<RentalDto> getRental( @PathVariable Long id) {
        RentalDto rental= rentalService.getRentalById(id);
        return ResponseEntity.ok(rental);
    }
}