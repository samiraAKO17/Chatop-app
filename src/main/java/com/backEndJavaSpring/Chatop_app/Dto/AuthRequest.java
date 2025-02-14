package com.backEndJavaSpring.Chatop_app.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String  email;
    private String password;

}
