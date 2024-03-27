package com.neudesic.appointmentmanagementsystem.domain.valueobjects;

import java.util.UUID;

public class PatientID extends BaseID<UUID>{
    public PatientID(UUID id) {
        super(id);
    }
}
