package com.neudesic.appointmentmanagementsystem.controller.dto;

import com.neudesic.appointmentmanagementsystem.domain.valueobjects.Department;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.Qualification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateDoctorDTO {
    private String name;
    private  Department department;
    private  Qualification qualification;
}
