package com.service.mental_health_therapy_center.Exceptions;

public class LoginException extends ApplicationException{
    public LoginException(String message) {

        super("Login Error: " + message);
    }
}
