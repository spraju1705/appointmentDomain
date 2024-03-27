package com.neudesic.appointmentmanagementsystem.controller.dto;

import com.neudesic.appointmentmanagementsystem.domain.entities.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryResponse {
    private Status status;
    private List<? extends Entity> entity;
}
