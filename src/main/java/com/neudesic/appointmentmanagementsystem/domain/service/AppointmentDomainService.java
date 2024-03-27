package com.neudesic.appointmentmanagementsystem.domain.service;

import com.neudesic.appointmentmanagementsystem.controller.dto.MaskedAppointmentResponse;
import com.neudesic.appointmentmanagementsystem.domain.entities.Appointment;
import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentConfirmEvent;

public interface AppointmentDomainService {
    public MaskedAppointmentResponse getMaskedAppointmentDetails(Appointment appointment);

}
