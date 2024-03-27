package com.neudesic.appointmentmanagementsystem.application.command;

import com.neudesic.appointmentmanagementsystem.controller.dto.Status;
import com.neudesic.appointmentmanagementsystem.controller.dto.UpdateResponse;
import com.neudesic.appointmentmanagementsystem.domain.entities.Doctor;
import com.neudesic.appointmentmanagementsystem.domain.event.DoctorCreateEvent;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.DoctorDetails;
import com.neudesic.appointmentmanagementsystem.infrastructure.entity.DoctorEntity;
import com.neudesic.appointmentmanagementsystem.infrastructure.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateDoctorCommandHandler implements CommandHandlerMethod<CreateDoctorCommand>{

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UpdateResponse handle(CreateDoctorCommand command) {
        Doctor doctor = Doctor.builder().
                doctorDetails(DoctorDetails.builder().name(command.getDto().getName()).department(command.getDto().getDepartment()).qualification(command.getDto().getQualification()).build())
                        .id(UUID.randomUUID()).build();
        doctor.apply(new DoctorCreateEvent());

        var doctorEntity = doctorRepository.save(modelMapper.map(doctor, DoctorEntity.class));

        return UpdateResponse.builder().id(doctorEntity.getId()).status(Status.SUCCESS).message("Doctor created successfully").build();

    }
}
