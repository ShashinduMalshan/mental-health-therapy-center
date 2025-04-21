package com.service.mental_health_therapy_center.Exceptions;

public class SchedulingConflictException extends ApplicationException {
    public SchedulingConflictException(String message) {
        super("Scheduling Conflict : " + message);
    }
}
