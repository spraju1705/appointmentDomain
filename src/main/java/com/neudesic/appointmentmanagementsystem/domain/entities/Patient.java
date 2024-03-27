package com.neudesic.appointmentmanagementsystem.domain.entities;

import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentDomainEvent;
import com.neudesic.appointmentmanagementsystem.domain.exceptions.AppointmentDomainException;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Patient extends AggregateRoot{

    public Patient(UUID id, BioData bioData, List<MedicalCondition> medicalCondition, PatientStatus patientStatus) {
        super(id);
        this.bioData = bioData;
        this.medicalCondition = medicalCondition;
        this.patientStatus = patientStatus;
    }

    private BioData bioData;

    private List<MedicalCondition> medicalCondition;

    private PatientStatus patientStatus;


    @Override
    public <R extends AppointmentDomainEvent> void apply(R event) {

    }
}
