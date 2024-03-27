package com.neudesic.appointmentmanagementsystem.infrastructure.entity;

import com.neudesic.appointmentmanagementsystem.domain.valueobjects.Department;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.Qualification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DoctorDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    private  String name;
    @Enumerated(EnumType.STRING)
    private  Department department;
    @Enumerated(EnumType.STRING)
    private  Qualification qualification;
}
