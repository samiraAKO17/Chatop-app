package com.backEndJavaSpring.Chatop_app.Repository;

import com.backEndJavaSpring.Chatop_app.Entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
