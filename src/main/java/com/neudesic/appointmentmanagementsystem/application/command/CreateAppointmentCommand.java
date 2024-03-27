package com.neudesic.appointmentmanagementsystem.application.command;

import com.neudesic.appointmentmanagementsystem.controller.dto.CreateAppointmentDTO;
import com.neudesic.appointmentmanagementsystem.controller.dto.CreateDoctorDTO;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
public class CreateAppointmentCommand extends BaseCommand<CreateAppointmentDTO, UUID> {
    public CreateAppointmentCommand(UUID id, CreateAppointmentDTO dto) {
        super(id, dto);
    }
}