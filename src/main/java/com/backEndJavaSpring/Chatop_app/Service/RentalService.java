package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;
import com.backEndJavaSpring.Chatop_app.Dto.RentalRequest;

import java.util.List;

public interface RentalService {
    List<RentalDto> rentals();
    RentalDto addRental (RentalRequest rental);
    RentalDto updateRental (RentalRequest rental, Long id);
    RentalDto getRentalById(Long id);
}
