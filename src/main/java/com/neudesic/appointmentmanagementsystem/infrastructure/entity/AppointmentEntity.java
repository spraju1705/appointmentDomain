package com.neudesic.appointmentmanagementsystem.infrastructure.entity;

import com.neudesic.appointmentmanagementsystem.domain.entities.Doctor;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "Appointment")
@Data
public class AppointmentEntity {

    @Id
    private UUID id;
    @OneToOne
    private DoctorEntity doctor;

    private UUID patientID;
    @OneToOne(cascade = CascadeType.ALL)
    private PrescriptionEntity prescriptionEntity;
    @OneToOne(cascade = CascadeType.ALL)
    private AppointmentDetailsEntity appointmentDetails;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;
}
