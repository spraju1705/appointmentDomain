package com.neudesic.appointmentmanagementsystem.application.command;

import com.neudesic.appointmentmanagementsystem.controller.dto.CreatePrescriptionDTO;
import lombok.experimental.SuperBuilder;

import java.util.UUID;
@SuperBuilder
public class CreatePrescriptionCommand extends BaseCommand<CreatePrescriptionDTO, UUID>{
}
