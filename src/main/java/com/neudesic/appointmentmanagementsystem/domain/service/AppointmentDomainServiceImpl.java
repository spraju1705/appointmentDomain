package com.neudesic.appointmentmanagementsystem.domain.service;

import com.neudesic.appointmentmanagementsystem.controller.dto.MaskedAppointmentResponse;
import com.neudesic.appointmentmanagementsystem.domain.entities.Appointment;
import com.neudesic.appointmentmanagementsystem.domain.entities.Patient;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.AppointmentDetails;
import com.neudesic.appointmentmanagementsystem.infrastructure.http.PatientDomainExternalServiceRetriever;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppointmentDomainServiceImpl implements AppointmentDomainService{

    @Autowired
    PatientDomainExternalServiceRetriever patientDomainExternalServiceRetriever;

    @Override
    public MaskedAppointmentResponse getMaskedAppointmentDetails(Appointment appointment) {
        //appointment fee is masked
        appointment.setAppointmentDetails(AppointmentDetails.builder().time(appointment.getAppointmentDetails().getTime()).build());
        Patient patient;
        try {
            patient= patientDomainExternalServiceRetriever.getPatientInfo(appointment.getPatientID());
        } catch (Exception e) {
            //fallback logic
            log.error("Patient Domain service is down");
            patient = Patient.builder().build();
        }
        return MaskedAppointmentResponse.builder().appointment(appointment).bioData(patient.getBioData()).medicalCondition(patient.getMedicalCondition()).build();
    }
}
