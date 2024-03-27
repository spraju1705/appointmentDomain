package com.neudesic.appointmentmanagementsystem.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class UpdateResponse {

    private Status status;

    private UUID id;

    private String message;
}
