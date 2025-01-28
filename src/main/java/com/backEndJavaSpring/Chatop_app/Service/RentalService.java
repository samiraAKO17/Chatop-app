package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Entity.Rental;

import java.util.List;

public interface RentalService {
    List<Rental> rentals();
    void addRental (Rental rental);
    void updateRental (Rental rental);
    void deleteRental (Rental rental);
    Rental getRentalById(Long id);
}
