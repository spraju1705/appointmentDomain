package com.neudesic.appointmentmanagementsystem.infrastructure.repository;

import com.neudesic.appointmentmanagementsystem.infrastructure.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, UUID> {
}
