package com.backEndJavaSpring.Chatop_app.Service;

import com.backEndJavaSpring.Chatop_app.Dto.RentalDto;
import com.backEndJavaSpring.Chatop_app.Entity.Rental;
import com.backEndJavaSpring.Chatop_app.Mapper.RentalMapper;
import com.backEndJavaSpring.Chatop_app.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalServiceImp implements RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalMapper rentalMapper;
    @Override
    public List<RentalDto> rentals() {
        return rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RentalDto addRental(RentalDto rental) {
        return rentalMapper.toDto(rentalRepository.save(rentalMapper.toEntity(rental)));
    }

    @Override
    public RentalDto updateRental(RentalDto rental) {
        Rental rentalEntity = rentalMapper.toEntity(rental);
        Rental rentalToUpdate = rentalRepository.findById(rentalEntity.getId()).get();
        rentalToUpdate.setName(rentalEntity.getName());
        rentalToUpdate.setOwner(rentalEntity.getOwner());
        rentalToUpdate.setPrice(rentalEntity.getPrice());
        rentalToUpdate.setSurface(rentalEntity.getSurface());
        rentalToUpdate.setDescription(rentalEntity.getDescription());
        rentalToUpdate.setCreated_at(rentalEntity.getCreated_at());
        rentalToUpdate.setUpdated_at(rentalEntity.getUpdated_at());
        return rentalMapper.toDto(rentalRepository.save(rentalToUpdate));
    }

    @Override
    public void deleteRental(RentalDto rental) {
        rentalRepository.delete(rentalMapper.toEntity(rental));
    }

    @Override
    public RentalDto getRentalById(Long id) {

        return rentalMapper.toDto(rentalRepository.existsById(id) ? rentalRepository.findById(id).get() : null);
    }
}
