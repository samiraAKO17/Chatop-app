package com.backEndJavaSpring.Chatop_app.Controller;

import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;
import com.backEndJavaSpring.Chatop_app.Dto.UserDto;
import com.backEndJavaSpring.Chatop_app.Service.RentalService;
import com.backEndJavaSpring.Chatop_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RentalController {
    @Autowired
    private RentalService s;
    @Autowired
    private UserService userService;

    @PostMapping("Rentals")
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rental) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        rental.setOwner(user);
        return ResponseEntity.ok(s.addRental(rental));
    }
    @GetMapping("Rentals")
    public List<RentalDto> getAll(){
        return s.rentals();
    }
    @PutMapping("Rentals/{id}")
    public RentalDto UpdatetRental(@RequestBody RentalDto rental, @PathVariable Long id) {
        System.out.print(rental.toString());
        rental.setId(id);
        return s.updateRental(rental);
    }
    @GetMapping("Rentals/{id}")
    public RentalDto getRental( @PathVariable Long id) {
        System.out.print(id);
        RentalDto rental= s.getRentalById(id);
        return rental;
    }
}
