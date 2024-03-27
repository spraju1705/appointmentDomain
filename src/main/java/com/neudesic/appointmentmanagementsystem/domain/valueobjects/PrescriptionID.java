package com.neudesic.appointmentmanagementsystem.domain.valueobjects;

import java.util.UUID;

public class PrescriptionID extends BaseID<UUID>{
    protected PrescriptionID(UUID id) {
        super(id);
    }
}
