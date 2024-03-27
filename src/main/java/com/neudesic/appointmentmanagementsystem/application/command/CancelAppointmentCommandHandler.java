package com.neudesic.appointmentmanagementsystem.application.command;

import com.neudesic.appointmentmanagementsystem.controller.dto.Status;
import com.neudesic.appointmentmanagementsystem.controller.dto.UpdateResponse;
import com.neudesic.appointmentmanagementsystem.domain.exceptions.AppointmentDomainException;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.AppointmentStatus;
import com.neudesic.appointmentmanagementsystem.infrastructure.repository.AppointmentRepository;
import com.neudesic.appointmentmanagementsystem.infrastructure.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.UUID;

@Component
public class CancelAppointmentCommandHandler implements CommandHandlerMethod<CancelAppointmentCommand>{

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;
    @Override
    @Transactional
    public UpdateResponse handle(CancelAppointmentCommand command) {
        var appointment = appointmentRepository.findById(command.getId());
        if (appointment.isEmpty()){
            throw new AppointmentDomainException("No appointment exist for :"+ command.getId());
        }else {
            appointment.get().setAppointmentStatus(AppointmentStatus.CANCELLED);
            var patientIDS= appointment.get().getDoctor().getPatientIDs();
            patientIDS.remove(appointment.get().getPatientID());
            appointmentRepository.flush();
        }
        return UpdateResponse.builder().status(Status.SUCCESS).id(appointment.get().getId()).message("cancelled").build();
    }
}
