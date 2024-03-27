package com.neudesic.appointmentmanagementsystem.infrastructure.repository;

import com.neudesic.appointmentmanagementsystem.infrastructure.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, UUID> {
}
