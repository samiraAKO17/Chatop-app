package com.backEndJavaSpring.Chatop_app.Dto;

import com.backEndJavaSpring.Chatop_app.Entity.Rental;
import com.backEndJavaSpring.Chatop_app.Entity.User;
import lombok.*;

import java.util.Date;
@Data
public class MessageDto {

    private Long id;
    private Rental rental;
    private User user;
    private String message;
    private Date created_at;
    private Date updated_at;

}
