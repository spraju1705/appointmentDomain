package com.neudesic.appointmentmanagementsystem.domain.entities;

import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentDomainEvent;
import com.neudesic.appointmentmanagementsystem.domain.event.DoctorCreateEvent;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Doctor extends AggregateRoot{

    private List<UUID> patientIDs;
    private List<UUID> prescriptionIDs;
    private DoctorDetails doctorDetails;
    private DoctorAvailabilityStatus doctorAvailabilityStatus;


    @Override
    public <R extends AppointmentDomainEvent> void apply(R event) {
        if (event instanceof DoctorCreateEvent){
            this.doctorAvailabilityStatus= DoctorAvailabilityStatus.AVAILABLE;
        }
    }
}



