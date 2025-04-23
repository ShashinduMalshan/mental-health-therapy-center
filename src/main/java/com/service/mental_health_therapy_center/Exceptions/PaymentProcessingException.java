package com.service.mental_health_therapy_center.Exceptions;

public class PaymentProcessingException extends ApplicationException{
    public PaymentProcessingException(String message) {
        super("Payment Processing Error: " + message);
    }
}
