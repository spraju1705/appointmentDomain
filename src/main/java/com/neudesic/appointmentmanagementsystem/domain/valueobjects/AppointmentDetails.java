package com.neudesic.appointmentmanagementsystem.domain.valueobjects;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@Data
@NoArgsConstructor
public class AppointmentDetails {

    private  LocalDateTime time;

    private  Double fees;


    public AppointmentDetails(LocalDateTime time, Double fees) {
        this.time = time;
        this.fees = fees;
    }
}
