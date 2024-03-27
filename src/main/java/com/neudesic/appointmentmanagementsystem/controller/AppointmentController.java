package com.neudesic.appointmentmanagementsystem.controller;

import com.neudesic.appointmentmanagementsystem.application.command.AppointmentDomainCommandDispatcher;
import com.neudesic.appointmentmanagementsystem.application.command.CancelAppointmentCommand;
import com.neudesic.appointmentmanagementsystem.application.command.CreateAppointmentCommand;
import com.neudesic.appointmentmanagementsystem.application.query.AppointmentDomainQueryDispatcher;
import com.neudesic.appointmentmanagementsystem.application.query.GetPatientAppointmentDetailsQuery;
import com.neudesic.appointmentmanagementsystem.controller.dto.*;
import com.neudesic.appointmentmanagementsystem.domain.entities.Appointment;
import com.neudesic.appointmentmanagementsystem.domain.service.AppointmentDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class AppointmentController {

    @Autowired
    AppointmentDomainCommandDispatcher appointmentDomainCommandDispatcher;

    @Autowired
    AppointmentDomainQueryDispatcher appointmentDomainQueryDispatcher;

    @Autowired
    AppointmentDomainService appointmentDomainService;

    @PostMapping("/createAppointment")
    public ResponseEntity<UpdateResponse> createAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO){
        try {
            var response = appointmentDomainCommandDispatcher.send(CreateAppointmentCommand.builder().id(UUID.randomUUID()).dto(createAppointmentDTO).build());
            log.info("Appointment created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error occurred in creating appointment due to"+ e.getStackTrace().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UpdateResponse.builder().status(Status.FAILURE).message("Error in creating appointment").build());
        }
    }

    @PatchMapping("/cancelAppointment/{id}")
    public ResponseEntity<UpdateResponse> cancelAppointment(@PathVariable String id){
        try {
            var response = appointmentDomainCommandDispatcher.send(CancelAppointmentCommand.builder().id(UUID.fromString(id)).build());
            log.info("Cancelled successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            log.error("Error occurred while cancelling due to "+ e.getStackTrace().toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UpdateResponse.builder().status(Status.FAILURE).message("Error in cancelling appointment").build());
    }

    @GetMapping("/getAppointmentDetails/{id}")
    public ResponseEntity<QueryResponse> getAppointmentDetails(@PathVariable String id) {
        try {
            var response = appointmentDomainQueryDispatcher.send(GetPatientAppointmentDetailsQuery.builder().id(UUID.fromString(id)).build());
            log.info("Fetched Appointment details successfully");
            return ResponseEntity.status(HttpStatus.OK).body(QueryResponse.builder().entity(response).status(Status.SUCCESS).build());
        } catch (Exception e) {
            log.error("Error occurred while cancelling due to " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(QueryResponse.builder().entity(List.of()).status(Status.FAILURE).build());
        }
    }

    @GetMapping("/getMaskedAppointmentDetails/{id}")
    public ResponseEntity<MaskedAppointmentResponse> getMaskedAppointmentDetailsForDoctor(@PathVariable String id) {
        try {
            var response = appointmentDomainQueryDispatcher.send(GetPatientAppointmentDetailsQuery.builder().id(UUID.fromString(id)).build());
            log.info("Fetched UnMaskedAppointment details successfully");
            var maskedAppointmentDetails = appointmentDomainService.getMaskedAppointmentDetails((Appointment) response.get(0));
            return ResponseEntity.status(HttpStatus.OK).body(maskedAppointmentDetails);
        } catch (Exception e) {
            log.error("Error occurred while cancelling due to " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MaskedAppointmentResponse.builder().build());
        }
    }

}
