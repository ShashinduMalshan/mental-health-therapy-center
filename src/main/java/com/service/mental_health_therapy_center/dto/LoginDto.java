package com.service.mental_health_therapy_center.dto;

import lombok.Getter;
import lombok.Setter;


    @Setter
    @Getter
public class LoginDto {
    private String name;

    private String password;

    private String role;

    public LoginDto() {
    }

    public LoginDto(String name, String password, String role) {
        this.name = name;

        this.password = password;
        this.role = role;


    }


    }
