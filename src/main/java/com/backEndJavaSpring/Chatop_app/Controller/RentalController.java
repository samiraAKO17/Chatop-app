package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;
import com.backEndJavaSpring.Chatop_app.Service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RentalController {
    @Autowired
    private RentalService s;

    @PostMapping("Rentals")
    public RentalDto createUser(@RequestBody RentalDto rental) {
        System.out.print(rental.toString());
        s.addRental(rental);
        return rental;
    }
    @GetMapping("Rental")
    public List<RentalDto> getAll(){
        return s.rentals();
    }
    @PutMapping("Rentals/{id}")
    public RentalDto UpdatetRental(@RequestBody RentalDto rental, @PathVariable Long id) {
        System.out.print(rental.toString());
        rental.setId(id);
        s.updateRental(rental);
        return rental;
    }
    @GetMapping("Rentals/{id}")
    public RentalDto getRental( @PathVariable Long id) {
        System.out.print(id);
        RentalDto rental= s.getRentalById(id);
        return rental;
    }
}
