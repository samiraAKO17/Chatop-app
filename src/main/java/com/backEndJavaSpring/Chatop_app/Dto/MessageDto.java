package com.backEndJavaSpring.Chatop_app.Dto;

import lombok.*;

import java.time.LocalDateTime;
@Data
public class MessageDto {

    private Long id;
    private Long rental_id;
    private Long user_id;
    private String message;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
