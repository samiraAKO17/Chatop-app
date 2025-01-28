package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Entity.Rental;
import com.backEndJavaSpring.Chatop_app.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImp implements RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Override
    public List<Rental> rentals() {
        return rentalRepository.findAll();
    }

    @Override
    public void addRental(Rental rental) {
        rentalRepository.save(rental);
    }

    @Override
    public void updateRental(Rental rental) {
        rentalRepository.save(rental);
    }

    @Override
    public void deleteRental(Rental rental) {
        rentalRepository.delete(rental);
    }

    @Override
    public Rental getRentalById(Long id) {

        return rentalRepository.existsById(id) ? rentalRepository.findById(id).get() : null;
    }
}
