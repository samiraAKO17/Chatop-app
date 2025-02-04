package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Entity.Rental;
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
    public Rental createUser(@RequestBody Rental rental) {
        System.out.print(rental.toString());
        s.addRental(rental);
        return rental;
    }
    @GetMapping("Rental")
    public List<Rental> getAll(){
        return s.rentals();
    }
    @PutMapping("Rentals/{id}")
    public Rental UpdatetRental(@RequestBody Rental rental, @PathVariable Long id) {
        System.out.print(rental.toString());
        rental.setId(id);
        s.updateRental(rental);
        return rental;
    }
    @GetMapping("Rentals/{id}")
    public Rental getRental( @PathVariable Long id) {
        System.out.print(id);
        Rental rental= s.getRentalById(id);
        return rental;
    }
}
