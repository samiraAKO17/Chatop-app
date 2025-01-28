package com.backEndJavaSpring.Chatop_app.Repository;

import com.backEndJavaSpring.Chatop_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> id(Long id);
    User findByEmail(String email);
}
