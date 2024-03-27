package com.neudesic.appointmentmanagementsystem.domain.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@SuperBuilder
public class DoctorDetails {

    private final String name;
    private final Department department;
    private final Qualification qualification;

}
