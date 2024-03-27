package com.neudesic.appointmentmanagementsystem.application.query;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class GetPatientAppointmentDetailsQuery extends BaseQuery{
    private UUID id;
}
