package com.backEndJavaSpring.Chatop_app.Repository;

import com.backEndJavaSpring.Chatop_app.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
