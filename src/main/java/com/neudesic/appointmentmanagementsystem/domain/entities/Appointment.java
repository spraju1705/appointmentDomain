package com.neudesic.appointmentmanagementsystem.domain.entities;

import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentCancelEvent;
import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentConfirmEvent;
import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentDomainEvent;
import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentDraftCreateEvent;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.AppointmentStatus;
import com.neudesic.appointmentmanagementsystem.domain.exceptions.AppointmentDomainException;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Appointment extends AggregateRoot{

    private UUID doctorID;
    private UUID patientID;
    private UUID prescriptionID;

    private AppointmentDetails appointmentDetails;

    private AppointmentStatus appointmentStatus;


    @Override
    public <R extends AppointmentDomainEvent> void apply(R event) {
            if (event instanceof AppointmentDraftCreateEvent){
                this.appointmentStatus = AppointmentStatus.DRAFT;
            } else if (event instanceof AppointmentConfirmEvent) {
                this.appointmentStatus = AppointmentStatus.CONFIRMED;
            } else if (event instanceof AppointmentCancelEvent) {
                this.appointmentStatus = AppointmentStatus.CANCELLED;
            }
    }
}
