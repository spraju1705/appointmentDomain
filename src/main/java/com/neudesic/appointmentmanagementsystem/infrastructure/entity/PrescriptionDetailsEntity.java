package com.neudesic.appointmentmanagementsystem.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PrescriptionDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    private  String medicine;

    private  String dozage;
}
