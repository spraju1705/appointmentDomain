package com.neudesic.appointmentmanagementsystem.controller;

import com.neudesic.appointmentmanagementsystem.application.command.AppointmentDomainCommandDispatcher;
import com.neudesic.appointmentmanagementsystem.application.command.CreateDoctorCommand;
import com.neudesic.appointmentmanagementsystem.application.command.CreatePrescriptionCommand;
import com.neudesic.appointmentmanagementsystem.controller.dto.CreateDoctorDTO;
import com.neudesic.appointmentmanagementsystem.controller.dto.CreatePrescriptionDTO;
import com.neudesic.appointmentmanagementsystem.controller.dto.Status;
import com.neudesic.appointmentmanagementsystem.controller.dto.UpdateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PrescriptionController {

    @Autowired
    AppointmentDomainCommandDispatcher appointmentDomainCommandDispatcher;


    @PostMapping("/createPrescription")
    public ResponseEntity<UpdateResponse> createPrescription(@RequestBody CreatePrescriptionDTO createPrescriptionDTO){
        try {
            var command = CreatePrescriptionCommand.builder().id(UUID.randomUUID()).dto(createPrescriptionDTO).build();
            var response = appointmentDomainCommandDispatcher.send(command);
            log.info("Prescription created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error occurred while creating doctor"+ e.getStackTrace().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UpdateResponse.builder().status(Status.FAILURE).message("Error occurred in creating prescription").build());
        }
    }
}
