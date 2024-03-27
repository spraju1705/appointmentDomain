package com.neudesic.appointmentmanagementsystem.application.command;

import com.neudesic.appointmentmanagementsystem.controller.dto.CreateDoctorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
public class CreateDoctorCommand extends BaseCommand<CreateDoctorDTO, UUID> {
    public CreateDoctorCommand(UUID id, CreateDoctorDTO dto) {
        super(id, dto);
    }
}
