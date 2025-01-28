package com.backEndJavaSpring.Chatop_app.Repository;

import com.backEndJavaSpring.Chatop_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
