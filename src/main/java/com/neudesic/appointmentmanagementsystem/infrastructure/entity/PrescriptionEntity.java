package com.neudesic.appointmentmanagementsystem.infrastructure.entity;

import com.neudesic.appointmentmanagementsystem.domain.valueobjects.DoctorID;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PatientID;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PrescriptionDetails;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PrescriptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Prescription")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionEntity {
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private DoctorEntity doctor;
    private UUID patientID;

    @OneToOne(cascade = CascadeType.ALL)
    private PrescriptionDetailsEntity prescriptionDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private AppointmentEntity appointment;

    @Enumerated(EnumType.STRING)
    private PrescriptionStatus prescriptionStatus;
}
