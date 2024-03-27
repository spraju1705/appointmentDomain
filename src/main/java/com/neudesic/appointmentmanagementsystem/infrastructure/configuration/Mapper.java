package com.neudesic.appointmentmanagementsystem.infrastructure.configuration;

import com.neudesic.appointmentmanagementsystem.domain.entities.Appointment;
import com.neudesic.appointmentmanagementsystem.domain.entities.Doctor;
import com.neudesic.appointmentmanagementsystem.domain.entities.Prescription;
import com.neudesic.appointmentmanagementsystem.infrastructure.entity.AppointmentEntity;
import com.neudesic.appointmentmanagementsystem.infrastructure.entity.DoctorEntity;
import com.neudesic.appointmentmanagementsystem.infrastructure.entity.PrescriptionEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class Mapper {

    @Bean
    public ModelMapper getMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<DoctorEntity, Doctor>() {
            @Override
            protected void configure() {
                // Mapping rules for SourceClass1 to DestinationClass1
                if (source.getPrescriptionEntities()!=null){
                    map().setPrescriptionIDs(source.getPrescriptionEntities().stream().map(x-> x.getId()).collect(Collectors.toList()));
                }
                // Add more mapping rules as needed
            }
        });
        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentEntity>() {
            @Override
            protected void configure() {
                skip(destination.getDoctor());
            }
        });
        modelMapper.addMappings(new PropertyMap<Prescription, PrescriptionEntity>() {
            @Override
            protected void configure() {
                skip(destination.getDoctor());
                map().setPatientID(source.getPatientID().id);
            }
        });
        modelMapper.addMappings(new PropertyMap<AppointmentEntity, Appointment>() {
            @Override
            protected void configure() {
                skip(destination.getDoctorID());
                skip(destination.getPrescriptionID());
            }
        });


        return modelMapper;
    }
}
