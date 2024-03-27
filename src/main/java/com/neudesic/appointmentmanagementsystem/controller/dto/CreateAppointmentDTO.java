package com.neudesic.appointmentmanagementsystem.controller.dto;

import com.neudesic.appointmentmanagementsystem.domain.valueobjects.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CreateAppointmentDTO {

    private UUID doctorID;
    private UUID patientID;
    private UUID appointmentID;
    private LocalDateTime time;
    private Double fees;
}
