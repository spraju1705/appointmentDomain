package com.neudesic.appointmentmanagementsystem.infrastructure.repository;

import com.neudesic.appointmentmanagementsystem.infrastructure.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {
}
