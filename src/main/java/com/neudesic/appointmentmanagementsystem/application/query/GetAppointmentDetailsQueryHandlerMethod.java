package com.neudesic.appointmentmanagementsystem.application.query;

import com.neudesic.appointmentmanagementsystem.domain.entities.Appointment;
import com.neudesic.appointmentmanagementsystem.domain.entities.Entity;
import com.neudesic.appointmentmanagementsystem.domain.exceptions.AppointmentDomainException;
import com.neudesic.appointmentmanagementsystem.infrastructure.repository.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GetAppointmentDetailsQueryHandlerMethod implements QueryHandlerMethod<GetPatientAppointmentDetailsQuery>{

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<? extends Entity> handle(GetPatientAppointmentDetailsQuery query) {
        var appointment = appointmentRepository.findById(query.getId());
        if (appointment.isEmpty()){
            throw  new AppointmentDomainException("Invalid appointment ID");
        }
        var appointmentEntity = modelMapper.map(appointment.get(), Appointment.class);
        appointmentEntity.setDoctorID(appointment.get().getDoctor().getId());
        appointmentEntity.setPrescriptionID(appointment.get().getPrescriptionEntity().getId());
        return List.of(appointmentEntity);
    }
}
