package com.backEndJavaSpring.Chatop_app.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    private Long id;
    private String name;
    private Float surface;
    private Float price;
    private String picture;
    private String description;
    private UserDto owner;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
