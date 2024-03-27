package com.neudesic.appointmentmanagementsystem.controller.dto;

import com.neudesic.appointmentmanagementsystem.domain.entities.Appointment;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.BioData;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.MedicalCondition;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class MaskedAppointmentResponse {
    private Appointment appointment;
    private BioData bioData;

    private List<MedicalCondition> medicalCondition;
}
