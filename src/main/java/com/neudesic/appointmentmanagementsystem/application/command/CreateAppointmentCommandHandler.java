package com.neudesic.appointmentmanagementsystem.application.command;

import com.neudesic.appointmentmanagementsystem.controller.dto.GetPatientDomainResponse;
import com.neudesic.appointmentmanagementsystem.controller.dto.QueryResponse;
import com.neudesic.appointmentmanagementsystem.controller.dto.Status;
import com.neudesic.appointmentmanagementsystem.controller.dto.UpdateResponse;
import com.neudesic.appointmentmanagementsystem.domain.entities.Appointment;
import com.neudesic.appointmentmanagementsystem.domain.entities.Doctor;
import com.neudesic.appointmentmanagementsystem.domain.entities.Patient;
import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentConfirmEvent;
import com.neudesic.appointmentmanagementsystem.domain.event.AppointmentDraftCreateEvent;
import com.neudesic.appointmentmanagementsystem.domain.exceptions.AppointmentDomainException;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.AppointmentDetails;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.DoctorAvailabilityStatus;
import com.neudesic.appointmentmanagementsystem.infrastructure.entity.AppointmentEntity;
import com.neudesic.appointmentmanagementsystem.infrastructure.http.PatientDomainExternalServiceRetriever;
import com.neudesic.appointmentmanagementsystem.infrastructure.repository.AppointmentRepository;
import com.neudesic.appointmentmanagementsystem.infrastructure.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class CreateAppointmentCommandHandler implements CommandHandlerMethod<CreateAppointmentCommand>{

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PatientDomainExternalServiceRetriever patientDomainExternalServiceRetriever;

    @Override
    @Transactional
    public UpdateResponse handle(CreateAppointmentCommand command) {
        var doctor = doctorRepository.findById(command.getDto().getDoctorID());
        if (doctor.isEmpty() ||
                doctor.get().getDoctorAvailabilityStatus()== DoctorAvailabilityStatus.UNAVAILABLE)
            throw new AppointmentDomainException("Doctor not available");

        patientDomainExternalServiceRetriever.getPatientInfo(command.getDto().getPatientID());
        var appointment = Appointment.builder().id(command.getId()).
                doctorID(command.getDto().getDoctorID()).patientID(command.getDto().getPatientID())
                .appointmentDetails(AppointmentDetails.builder().time(command.getDto().getTime()).fees(command.getDto().getFees()).build()).build();
        appointment.apply(new AppointmentConfirmEvent());
        var appointmentEntityToDB = modelMapper.map(appointment, AppointmentEntity.class);
        appointmentEntityToDB.setDoctor(doctor.get());
        var appointmentEntityFromDB = appointmentRepository.save(appointmentEntityToDB);
        doctor.get().setPatientIDs(List.of(command.getDto().getPatientID()));
        doctorRepository.flush();

        return UpdateResponse.builder().id(appointmentEntityFromDB.getId()).status(Status.SUCCESS).
                message("Appointment ID: " + appointmentEntityFromDB.getId()+" created successfully").build();
    }
}
