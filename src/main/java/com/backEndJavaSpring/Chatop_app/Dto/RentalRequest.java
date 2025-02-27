package com.backEndJavaSpring.Chatop_app.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RentalRequest {
    //  @NotNull
    private String name;
    private Float surface;
    private Float price;
    private String description;
    private MultipartFile picture;  // 🖼️ Fichier image
}

