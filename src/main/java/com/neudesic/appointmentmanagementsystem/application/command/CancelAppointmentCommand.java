package com.neudesic.appointmentmanagementsystem.application.command;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.UUID;
@SuperBuilder
public class CancelAppointmentCommand extends BaseCommand<UUID, UUID>{
}
