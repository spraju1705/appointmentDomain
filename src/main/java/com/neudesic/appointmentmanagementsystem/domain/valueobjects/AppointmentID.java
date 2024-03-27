package com.neudesic.appointmentmanagementsystem.domain.valueobjects;

import java.util.UUID;

public class AppointmentID extends BaseID<UUID>{
    protected AppointmentID(UUID id) {
        super(id);
    }
}
