package com.neudesic.appointmentmanagementsystem.infrastructure.entity;

import com.neudesic.appointmentmanagementsystem.domain.valueobjects.DoctorAvailabilityStatus;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.DoctorDetails;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PatientID;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PrescriptionID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorEntity {

    @Id
    private UUID id;

    @ElementCollection
    private List<UUID> patientIDs = new ArrayList<>();

    @OneToMany
    private List<PrescriptionEntity> prescriptionEntities = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private DoctorDetailsEntity doctorDetails;

    @Enumerated(EnumType.STRING)
    private DoctorAvailabilityStatus doctorAvailabilityStatus;

}
