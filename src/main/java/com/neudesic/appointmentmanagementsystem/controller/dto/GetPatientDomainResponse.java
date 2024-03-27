package com.neudesic.appointmentmanagementsystem.controller.dto;

import com.neudesic.appointmentmanagementsystem.domain.entities.Entity;
import com.neudesic.appointmentmanagementsystem.domain.entities.Patient;
import lombok.Data;

import java.util.List;
@Data
public class GetPatientDomainResponse {
    private Status status;
    private List<Patient> entity;
}
