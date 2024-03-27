package com.neudesic.appointmentmanagementsystem.controller.dto;

import com.neudesic.appointmentmanagementsystem.domain.valueobjects.DoctorID;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PatientID;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PrescriptionDetails;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PrescriptionStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class CreatePrescriptionDTO {
//    private UUID doctorID;
//    private UUID patientID;
    private UUID appointmentID;

    private PrescriptionDetails prescriptionDetails;
}
