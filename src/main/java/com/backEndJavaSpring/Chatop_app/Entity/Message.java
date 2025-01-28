package com.backEndJavaSpring.Chatop_app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @ManyToOne
    private Rental rental;
    @Getter
    @Setter
    @ManyToOne
    private User user;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private Date created_at;
    @Getter
    @Setter
    private Date updated_at;


    public Message() {}
    public Message(String message) {}

}
