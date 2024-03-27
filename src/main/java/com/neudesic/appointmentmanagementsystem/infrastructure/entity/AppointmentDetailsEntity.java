package com.neudesic.appointmentmanagementsystem.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Table(name = "AppointmentDetails")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private  LocalDateTime time;

    private  Double fees;
}
