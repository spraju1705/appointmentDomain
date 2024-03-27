package com.neudesic.appointmentmanagementsystem.domain.service;

import com.neudesic.appointmentmanagementsystem.domain.entities.Prescription;
import com.neudesic.appointmentmanagementsystem.domain.event.PrescriptionCreateEvent;
import com.neudesic.appointmentmanagementsystem.domain.event.PrescriptionLinkEvent;
import com.neudesic.appointmentmanagementsystem.domain.event.PrescriptionUpdateEvent;

public interface PrescriptionDomainService {

    public PrescriptionCreateEvent createPrescription(Prescription prescription);

    public PrescriptionLinkEvent linkPrescription(Prescription prescription);

    public PrescriptionUpdateEvent updatePrescription(Prescription prescription);
}
