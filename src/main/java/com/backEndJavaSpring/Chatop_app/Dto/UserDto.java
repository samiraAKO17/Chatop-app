package com.backEndJavaSpring.Chatop_app.Dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
