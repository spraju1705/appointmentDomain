package com.neudesic.appointmentmanagementsystem.domain.valueobjects;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class PrescriptionDetails {

    private String medicine;

    private String dozage;

    public PrescriptionDetails(String medicine, String dozage) {
        this.medicine = medicine;
        this.dozage = dozage;
    }
}
