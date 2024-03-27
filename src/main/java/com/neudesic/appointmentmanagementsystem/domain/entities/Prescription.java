package com.neudesic.appointmentmanagementsystem.domain.entities;

import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentDomainEvent;
import com.neudesic.appointmentmanagementsystem.domain.event.PrescriptionCreateEvent;
import com.neudesic.appointmentmanagementsystem.domain.exceptions.AppointmentDomainException;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Prescription extends AggregateRoot{

    private DoctorID doctorID;
    private PatientID patientID;
    private AppointmentID appointmentID;

    private PrescriptionDetails prescriptionDetails;

    private PrescriptionStatus prescriptionStatus;

    @Override
    public <R extends AppointmentDomainEvent> void apply(R event) {
        if (event instanceof PrescriptionCreateEvent){
            this.prescriptionStatus = PrescriptionStatus.LINKED;
        }
    }
}
