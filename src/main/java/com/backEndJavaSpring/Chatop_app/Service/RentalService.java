package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;

import java.util.List;

public interface RentalService {
    List<RentalDto> rentals();
    void addRental (RentalDto rental);
    void updateRental (RentalDto rental);
    void deleteRental (RentalDto rental);
    RentalDto getRentalById(Long id);
}
