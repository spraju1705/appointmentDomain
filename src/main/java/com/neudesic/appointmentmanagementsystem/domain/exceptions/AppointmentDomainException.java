package com.neudesic.appointmentmanagementsystem.domain.exceptions;

public class AppointmentDomainException extends RuntimeException{

    public AppointmentDomainException(String message) {
        super(message);
    }

    public AppointmentDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
